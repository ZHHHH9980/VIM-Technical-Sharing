package com.example.todo;

public class TodoUtils {
    // TODO: Use 'gr' to go to the reference of this method
    public static boolean validateTask(String task) {
        return task != null && !task.trim().isEmpty();
    }

    public static String formatTask(String task) {
        return "✓ " + task.toUpperCase();
    }
}