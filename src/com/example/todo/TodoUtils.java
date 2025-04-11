package com.example.todo;  

// gr
public class TodoUtils {
    // 任务校验
    public static boolean validateTask(String task) {
        return task != null && !task.trim().isEmpty();
    }

    // 任务格式化
    public static String formatTask(String task) {
        return "✓ " + task.toUpperCase();
    }
}