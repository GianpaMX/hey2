package net.ddns.softux.hey.todoapp.savetask.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListInMemory;

import java.util.Map;

/**
 * Created by juan on 30/06/16.
 */

public class SaveTaskInMemory extends TaskListInMemory implements SaveTaskGateway {
    protected int counter;

    public SaveTaskInMemory() {
        super();
        counter = 0;
    }

    public SaveTaskInMemory(Map<String, TaskEntitity> tasks, int counter) {
        super(tasks);
        this.counter = counter;
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
