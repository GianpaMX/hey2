package net.ddns.softux.hey.todoapp.savetask.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.Task;
import net.ddns.softux.hey.todoapp.savetask.TaskEntitity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 30/06/16.
 */

public class SaveTaskInMemory implements SaveTaskGateway {
    protected Map<String, TaskEntitity> tasks;
    private int counter;

    public SaveTaskInMemory() {
        tasks = new HashMap<>();
        counter = 0;
    }

    public SaveTaskInMemory(Map<String, TaskEntitity> tasks, int counter) {
        this.tasks = tasks;
        this.counter = counter;
    }

    @Override
    public void save(Task task, SaveTaskGatewayCallback callback) {
        TaskEntitity taskEntitity;
        if (task.key != null && tasks.containsKey(task.key)) {
            taskEntitity = tasks.get(task.key);
            taskEntitity.copyFrom(task);
        } else {
            taskEntitity = new TaskEntitity(String.valueOf(++counter), task.title, task.description);
        }

        callback.onSuccess(taskEntitity);
    }
}
