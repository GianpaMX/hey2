package net.ddns.softux.hey.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;

import java.util.HashMap;
import java.util.Map;

public class TaskListInMemory implements TaskListGateway, SaveTaskGateway {
    protected OnTaskListGatewayListener onTaskListGatewayListener;
    protected Map<String, TaskEntitity> tasks;
    protected int counter;

    public TaskListInMemory() {
        tasks = new HashMap<>();
    }

    public TaskListInMemory(Map<String, TaskEntitity> tasks) {
        this.tasks = tasks;
    }

    public TaskListInMemory(Map<String, TaskEntitity> tasks, int counter) {
        this.tasks = tasks;
        this.counter = counter;
    }

    @Override
    public void setOnTaskListGatewayListener(OnTaskListGatewayListener onTaskListGatewayListener) {
        this.onTaskListGatewayListener = onTaskListGatewayListener;
    }

    @Override
    public void loadTaskList() {
        onTaskListGatewayListener.onTaskListLoad(tasks.values(), this);
    }

    @Override
    public void saveTaskList() {

    }

    public int size() {
        return tasks.values().size();
    }

    @Override
    public void save(Task task, SaveTaskGatewayCallback callback) {
        TaskEntitity taskEntitity;
        if (task.key != null && tasks.containsKey(task.key)) {
            taskEntitity = tasks.get(task.key);
            taskEntitity.copyFrom(task);
        } else {
            taskEntitity = new TaskEntitity(task);
            taskEntitity.key = String.valueOf(++counter);

            tasks.put(taskEntitity.key, taskEntitity);
            if (onTaskListGatewayListener != null) {
                onTaskListGatewayListener.onTaskAdded(taskEntitity, this);
            }
        }

        if (callback != null)
            callback.onSuccess(taskEntitity);
    }
}
