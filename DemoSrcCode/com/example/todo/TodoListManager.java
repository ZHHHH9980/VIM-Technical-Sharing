package com.example.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListManager {
    private List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        // TODO: Use 'gd' to check validateTask's definition
        if (TodoUtils.validateTask(task)) {
            tasks.add(task);
            System.out.println("Task added: " + task);
        } else {
            System.out.println("Invalid task!");
        }
    }

    public void showTasks() {
        System.out.println("Current Tasks:");
        for (String task : tasks) {
            System.out.println(TodoUtils.formatTask(task));
        }
    }

    public static void main(String[] args) {
        TodoListManager manager = new TodoListManager();
        manager.addTask("Buy milk");
        manager.addTask(""); // 会被校验拦截
        manager.addTask("Write code");
        manager.showTasks();
    }
}