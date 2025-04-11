package com.example.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListManager {
    private List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        // gd
        if (TodoUtils.validateTask(task)) {  // 调用工具类方法
            tasks.add(task);
            System.out.println("Task added: " + task);
        } else {
            System.out.println("Invalid task!");
        }
    }

    public void showTasks() {
        System.out.println("Current Tasks:");
        for (String task : tasks) {
            System.out.println(TodoUtils.formatTask(task));  // 调用工具类方法
        }
    }

    public static void main(String[] args) {
        TodoListManager manager = new TodoListManager();
        manager.addTask("Buy milk");
        manager.addTask("");  // 会被校验拦截
        manager.addTask("Write code");
        manager.showTasks();
    }
}