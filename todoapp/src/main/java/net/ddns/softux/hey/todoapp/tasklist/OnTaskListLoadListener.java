package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.Task;

import java.util.List;

/**
 * Created by juan on 3/07/16.
 */

public interface OnTaskListLoadListener {
    void onTaskListLoad(List<Task> tasks);
}
