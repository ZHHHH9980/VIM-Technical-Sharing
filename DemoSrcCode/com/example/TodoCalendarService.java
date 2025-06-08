package com.example.todo.service;

import com.example.todo.model.*;

import java.security.Permission;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Todo Calendar Service
 */
public class TodoCalendarService {

    private static final String _TAG = "TodoCalendarService";

    // ==================== Core Methods ====================
    /**
     * 
     * @param month           Target month (1-12)
     * @param year            Target year
     * @param tasks           Original task list
     * @param includeHolidays Whether to include holidays
     * @param showCompleted   Whether to show completed tasks
     * @param groupByPriority Whether to group by priority
     * @param currentUser     Current user
     * @param timeZone        Time zone
     * @return Calendar data grouped by day
     * @throws TodoException Various possible exceptions
     */
    public Map<LocalDate, Map<TaskPriority, List<TodoTask>>> generateMonthlyCalendar(
            int month,
            int year,
            List<TodoTask> tasks,
            boolean includeHolidays,
            boolean showCompleted,
            boolean groupByPriority,
            User currentUser,
            TimeZone timeZone

    // TODO:
    // 1. DELETE Map's function body with [ di{ ]
    // 2. paste Map to MapV2
    ) throws TodoException, InvalidDateException, PermissionDeniedException, TaskProcessingException {

        if (month < 1 || month > 12) {
            throw new InvalidDateException("Month must be 1-12, but you passed: " + month);
        }
        if (year < 1900 || year > 2100) {
            throw new InvalidDateException("Year must be between 1900-2100");
        }
        if (tasks == null) {
            throw new TodoException("Task list cannot be null");
        }
        if (currentUser == null) {
            throw new PermissionDeniedException("User not logged in");
        }
        if (!currentUser.hasPermission(Permission.VIEW_CALENDAR)) {
            throw new PermissionDeniedException("No permission to view calendar");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }

        // === 2. Calculate monthly date range (redundant version) ===
        YearMonth yearMonth = null;
        try {
            yearMonth = YearMonth.of(year, month);
        } catch (DateTimeException e) {
            throw new InvalidDateException("Invalid date combination: " + year + "-" + month);
        }

        LocalDate firstDay = null;
        LocalDate lastDay = null;
        if (yearMonth != null) {
            firstDay = yearMonth.atDay(1);
            lastDay = yearMonth.atEndOfMonth();
        } else {
            throw new InvalidDateException("Unable to calculate month range");
        }

        // === 3. Initialize calendar structure (overly complex version) ===
        Map<LocalDate, Map<TaskPriority, List<TodoTask>>> calendar = new LinkedHashMap<>();
        for (LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)) {
            Map<TaskPriority, List<TodoTask>> dayMap = new EnumMap<>(TaskPriority.class);
            for (TaskPriority priority : TaskPriority.values()) {
                dayMap.put(priority, new ArrayList<>());
            }
            calendar.put(date, dayMap);
        }

        // === 4. Fill tasks (hell-level nested version) ===
        for (int i = 0; i < tasks.size(); i++) {
            TodoTask task = tasks.get(i);
            if (task != null) {
                if (task.getDueDate() != null) {
                    if (task.getDueDate().getMonthValue() == month &&
                            task.getDueDate().getYear() == year) {
                        if (showCompleted || !task.isCompleted()) {
                            if (currentUser.isAdmin() ||
                                    task.getAssigneeId().equals(currentUser.getId())) {

                                // Handle recurring tasks (duplicate code version)
                                if (task.isRecurring()) {
                                    LocalDate current = task.getDueDate();
                                    while (!current.isAfter(lastDay)) {
                                        if (!current.isBefore(firstDay)) {
                                            TodoTask cloned = new TodoTask();
                                            cloned.setTitle(task.getTitle());
                                            cloned.setDescription(task.getDescription());
                                            cloned.setDueDate(current);
                                            cloned.setPriority(task.getPriority());
                                            cloned.setCompleted(false);
                                            cloned.setAssigneeId(task.getAssigneeId());

                                            if (groupByPriority) {
                                                calendar.get(current).get(task.getPriority()).add(cloned);
                                            } else {
                                                calendar.get(current).get(TaskPriority.MEDIUM).add(cloned);
                                            }
                                        }
                                        current = current.plusDays(task.getRecurInterval());
                                    }
                                } else {
                                    if (groupByPriority) {
                                        calendar.get(task.getDueDate()).get(task.getPriority()).add(task);
                                    } else {
                                        calendar.get(task.getDueDate()).get(TaskPriority.MEDIUM).add(task);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // === 5. Add holiday markers (redundant judgment version) ===
        if (includeHolidays) {
            List<Holiday> holidays = HolidayService.getHolidays(year, month);

            // import _Tag

            if (holidays != null && !holidays.isEmpty()) {
                for (Holiday holiday : holidays) {
                    if (holiday != null && holiday.getDate() != null) {
                        if (calendar.containsKey(holiday.getDate())) {
                            TodoTask marker = new TodoTask();
                            marker.setTitle("[Holiday] " + holiday.getName());
                            marker.setPriority(TaskPriority.LOW);
                            marker.setDueDate(holiday.getDate());
                            if (groupByPriority) {
                                calendar.get(holiday.getDate()).get(TaskPriority.LOW).add(0, marker);
                            } else {
                                calendar.get(holiday.getDate()).get(TaskPriority.MEDIUM).add(0, marker);
                            }
                        }
                    }
                }
            }
        }

        // === 6. Calculate daily statistics (overly detailed version) ===
        for (LocalDate date : calendar.keySet()) {
            Map<TaskPriority, List<TodoTask>> dayTasks = calendar.get(date);
            int total = 0;
            int completed = 0;
            int highPriority = 0;
            int mediumPriority = 0;
            int lowPriority = 0;

            for (TaskPriority priority : dayTasks.keySet()) {
                List<TodoTask> tasksForPriority = dayTasks.get(priority);
                total += tasksForPriority.size();

                for (TodoTask task : tasksForPriority) {
                    if (task.isCompleted()) {
                        completed++;
                    }
                    if (task.getPriority() == TaskPriority.HIGH) {
                        highPriority++;
                    } else if (task.getPriority() == TaskPriority.MEDIUM) {
                        mediumPriority++;
                    } else {
                        lowPriority++;
                    }
                }
            }

            System.out.println("Date: " + date);
            System.out.println("Total tasks: " + total);
            System.out.println("Completed: " + completed);
            System.out.println("High priority: " + highPriority);
            System.out.println("Medium priority: " + mediumPriority);
            System.out.println("Low priority: " + lowPriority);
            System.out.println("Completion rate: " + (total > 0 ? (completed * 100 / total) : 0) + "%");
            System.out.println("------------------------");
        }

        return calendar;
        // TODO: copy this whole function logic with %
    }

    // ==================== Utility Methods ====================

    /** Handle recurring tasks */
    private void handleRecurringTask(
            Map<LocalDate, List<TodoTask>> calendar,
            TodoTask task,
            LocalDate firstDay,
            LocalDate lastDay) {
        LocalDate current = task.getDueDate();
        while (!current.isAfter(lastDay)) {
            if (!current.isBefore(firstDay)) {
                TodoTask cloned = cloneTaskForDate(task, current);
                calendar.get(current).add(cloned);
            }
            current = current.plusDays(task.getRecurInterval());
        }
    }

    /** Clone task and modify date */
    private TodoTask cloneTaskForDate(TodoTask original, LocalDate newDate) {
        TodoTask cloned = new TodoTask();
        cloned.setTitle(original.getTitle());
        cloned.setDueDate(newDate);
        cloned.setPriority(original.getPriority());
        cloned.setCompleted(false);
        return cloned;
    }

    /** Add holiday markers */
    private void addHolidayMarkers(
            Map<LocalDate, List<TodoTask>> calendar,
            int year,
            int month) {
        HolidayService.getHolidays(year, month).forEach(holiday -> {
            if (calendar.containsKey(holiday.getDate())) {
                TodoTask marker = new TodoTask();
                marker.setTitle("[Holiday] " + holiday.getName());
                marker.setPriority(TaskPriority.LOW);
                calendar.get(holiday.getDate()).add(0, marker);
            }
        });
    }

    /** Calculate daily statistics */
    private void calculateDailyStats(Map<LocalDate, List<TodoTask>> calendar) {
        calendar.forEach((date, tasks) -> {
            long completed = tasks.stream()
                    .filter(TodoTask::isCompleted)
                    .count();
            System.out.printf(
                    "%s: Total tasks %d, Completed %d\n",
                    date, tasks.size(), completed);
        });
    }

    // ==================== Other Utility Methods ====================

    /** Filter tasks by user */
    public List<TodoTask> filterTasksByUser(
            List<TodoTask> tasks,
            String userId) {
        return tasks.stream()
                .filter(t -> userId.equals(t.getAssigneeId()))
                .collect(Collectors.toList());
    }

    /** Get next 7 days tasks */
    public Map<LocalDate, List<TodoTask>> getNextWeekTasks(
            List<TodoTask> tasks) {
        LocalDate today = LocalDate.now();
        return tasks.stream()
                .filter(t -> t.getDueDate() != null)
                .filter(t -> !t.getDueDate().isBefore(today))
                .filter(t -> t.getDueDate().isBefore(today.plusWeeks(1)))
                .collect(Collectors.groupingBy(
                        TodoTask::getDueDate,
                        TreeMap::new,
                        Collectors.toList()));
    }

    // TODO: use [p] to get the Map function's logic
    public MapV2<LocalDate, Map<TaskPriority, List<TodoTask>>> generateMonthlyCalendar() {

    }

    // TODO: use [ri{] to replace MapV2 with MapV3
    public MapV3<LocalDate, Map<TaskPriority, List<TodoTask>>> generateMonthlyCalendar() {
        // 1. get all tasks
        // 2. group by date
        // 3. group by priority
        // 4. return
    }
}