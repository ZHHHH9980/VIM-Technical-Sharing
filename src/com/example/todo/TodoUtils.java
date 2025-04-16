package com.example.todo;  

public class TodoUtils {
    // 任务校验
    // go to the reference of this method
    public static boolean validateTask(String task) {
        return task != null && !task.trim().isEmpty();
    }

    // 任务格式化
    public static String formatTask(String task) {
        return "✓ " + task.toUpperCase();
    }
}