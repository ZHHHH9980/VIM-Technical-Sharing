package com.example.todo.service;

import com.example.todo.model.*;

import java.security.Permission;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 待办事项日历服务
 */
public class TodoCalendarService {

    private static final String _TAG = "TodoCalendarService";

    // TODO: 
    // 1. DELETE ME (di{})
    // 2. paste Map to MapV2
    // ==================== 核心方法 ====================
    /**
     * 生成月历视图（故意写得很恶心的版本）
     * @param month 目标月份 (1-12)
     * @param year 目标年份
     * @param tasks 原始任务列表
     * @param includeHolidays 是否包含节假日
     * @param showCompleted 是否显示已完成任务
     * @param groupByPriority 是否按优先级分组
     * @param currentUser 当前用户
     * @param timeZone 时区
     * @return 按日分组的日历数据
     * @throws TodoException 各种可能的异常
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

    // TODO: Operation with this function {}
    ) throws TodoException, InvalidDateException, PermissionDeniedException, TaskProcessingException {
        // === 1. 参数验证（过度复杂版）===
        if (month < 1 || month > 12) {
            throw new InvalidDateException("月份必须为1-12，但你传入了：" + month);
        }
        if (year < 1900 || year > 2100) {
            throw new InvalidDateException("年份必须在1900-2100之间");
        }
        if (tasks == null) {
            throw new TodoException("任务列表不能为空");
        }
        if (currentUser == null) {
            throw new PermissionDeniedException("用户未登录");
        }
        if (!currentUser.hasPermission(Permission.VIEW_CALENDAR)) {
            throw new PermissionDeniedException("没有查看日历的权限");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }

        // === 2. 计算当月日期范围（冗余版）===
        YearMonth yearMonth = null;
        try {
            yearMonth = YearMonth.of(year, month);
        } catch (DateTimeException e) {
            throw new InvalidDateException("无效的日期组合：" + year + "-" + month);
        }
        
        LocalDate firstDay = null;
        LocalDate lastDay = null;
        if (yearMonth != null) {
            firstDay = yearMonth.atDay(1);
            lastDay = yearMonth.atEndOfMonth();
        } else {
            throw new InvalidDateException("无法计算月份范围");
        }

        // === 3. 初始化日历结构（过度复杂版）===
        Map<LocalDate, Map<TaskPriority, List<TodoTask>>> calendar = new LinkedHashMap<>();
        for (LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)) {
            Map<TaskPriority, List<TodoTask>> dayMap = new EnumMap<>(TaskPriority.class);
            for (TaskPriority priority : TaskPriority.values()) {
                dayMap.put(priority, new ArrayList<>());
            }
            calendar.put(date, dayMap);
        }
        
        // === 4. 填充任务（地狱级嵌套版）===
        for (int i = 0; i < tasks.size(); i++) {
            TodoTask task = tasks.get(i);
            if (task != null) {
                if (task.getDueDate() != null) {
                    if (task.getDueDate().getMonthValue() == month && 
                        task.getDueDate().getYear() == year) {
                        if (showCompleted || !task.isCompleted()) {
                            if (currentUser.isAdmin() || 
                                task.getAssigneeId().equals(currentUser.getId())) {
                                
                                // 处理重复任务（重复代码版）
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

        // === 5. 添加节假日标记（冗余判断版）===
        if (includeHolidays) {
            List<Holiday> holidays = HolidayService.getHolidays(year, month);
            
            // import _Tag
            
            if (holidays != null && !holidays.isEmpty()) {
                for (Holiday holiday : holidays) {
                    if (holiday != null && holiday.getDate() != null) {
                        if (calendar.containsKey(holiday.getDate())) {
                            TodoTask marker = new TodoTask();
                            marker.setTitle("[假日] " + holiday.getName());
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

        // === 6. 计算每日统计（过度详细版）===
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
            
            System.out.println("日期: " + date);
            System.out.println("总任务数: " + total);
            System.out.println("已完成: " + completed);
            System.out.println("高优先级: " + highPriority);
            System.out.println("中优先级: " + mediumPriority);
            System.out.println("低优先级: " + lowPriority);
            System.out.println("完成率: " + (total > 0 ? (completed * 100 / total) : 0) + "%");
            System.out.println("------------------------");
        }

        return calendar;
    // TODO: copy this whole function logic with %
    }

    // ==================== 工具方法 ====================

    /** 处理重复任务 */
    private void handleRecurringTask(
        Map<LocalDate, List<TodoTask>> calendar,
        TodoTask task,
        LocalDate firstDay,
        LocalDate lastDay
    ) {
        LocalDate current = task.getDueDate();
        while (!current.isAfter(lastDay)) {
            if (!current.isBefore(firstDay)) {
                TodoTask cloned = cloneTaskForDate(task, current);
                calendar.get(current).add(cloned);
            }
            current = current.plusDays(task.getRecurInterval());
        }
    }

    /** 克隆任务并修改日期 */
    private TodoTask cloneTaskForDate(TodoTask original, LocalDate newDate) {
        TodoTask cloned = new TodoTask();
        cloned.setTitle(original.getTitle());
        cloned.setDueDate(newDate);
        cloned.setPriority(original.getPriority());
        cloned.setCompleted(false);
        return cloned;
    }

    /** 添加节假日标记 */
    private void addHolidayMarkers(
        Map<LocalDate, List<TodoTask>> calendar, 
        int year, 
        int month
    ) {
        HolidayService.getHolidays(year, month).forEach(holiday -> {
            if (calendar.containsKey(holiday.getDate())) {
                TodoTask marker = new TodoTask();
                marker.setTitle("[假日] " + holiday.getName());
                marker.setPriority(TaskPriority.LOW);
                calendar.get(holiday.getDate()).add(0, marker);
            }
        });
    }

    /** 计算每日统计信息 */
    private void calculateDailyStats(Map<LocalDate, List<TodoTask>> calendar) {
        calendar.forEach((date, tasks) -> {
            long completed = tasks.stream()
                .filter(TodoTask::isCompleted)
                .count();
            System.out.printf(
                "%s: 总任务 %d, 已完成 %d\n", 
                date, tasks.size(), completed
            );
        });
    }

    // ==================== 其他短工具方法 ====================

    /** 过滤某用户的任务 */
    public List<TodoTask> filterTasksByUser(
        List<TodoTask> tasks, 
        String userId
    ) {
        return tasks.stream()
            .filter(t -> userId.equals(t.getAssigneeId()))
            .collect(Collectors.toList());
    }

    /** 获取未来7天的任务 */
    public Map<LocalDate, List<TodoTask>> getNextWeekTasks(
        List<TodoTask> tasks
    ) {
        LocalDate today = LocalDate.now();
        return tasks.stream()
            .filter(t -> t.getDueDate() != null)
            .filter(t -> !t.getDueDate().isBefore(today))
            .filter(t -> t.getDueDate().isBefore(today.plusWeeks(1)))
            .collect(Collectors.groupingBy(
                TodoTask::getDueDate,
                TreeMap::new,
                Collectors.toList()
            ));
    }

    public MapV2<LocalDate, Map<TaskPriority, List<TodoTask>>> generateMonthlyCalendar(){

    }
}