package net.ddns.softux.hey.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 3/07/16.
 */

public class TaskListInMemory implements TaskListGateway {
    protected Map<String, TaskEntitity> tasks;

    public TaskListInMemory() {
        tasks = new HashMap<>();
    }

    public TaskListInMemory(Map<String, TaskEntitity> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void loadTaskList(OnTaskListGatewayListener onTaskListGatewayListener) {
        onTaskListGatewayListener.onTaskListLoad(tasks.values());
    }

    public int size() {
        return tasks.values().size();
    }
}
