package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.Task;

import java.util.List;

public interface OnTaskListLoadListener {
    void onTaskListLoad(List<Task> tasks);

    void onTaskAdded(Task task);
}
