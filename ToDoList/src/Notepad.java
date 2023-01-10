package main;

import main.Model.Tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notepad {

    private static int currentId = 1;
    private static final Map<Integer, Tasks> tasks = new HashMap<>();

    public static List<Tasks> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public static int addTask(Tasks task) {
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Tasks getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }
    public static Tasks deleteTaskId (int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.remove(taskId);
        }
        return null;
    }

    public static void deleteAll () {
            tasks.clear();
    }

    public static int upDate (int id) {
        Tasks task = new Tasks();
        task.setId(currentId);
        tasks.put(id, tasks.getOrDefault(currentId + 1, task));
        return currentId;
    }
}