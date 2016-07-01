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
    protected Map<String, TaskEntitity> tasks = new HashMap<>();
    private int counter = 0;

    @Override
    public void save(Task task, SaveTaskGatewayCallback callback) {
        TaskEntitity taskEntitity;
        if (task.key != null && tasks.containsKey(task.key)) {
            taskEntitity = tasks.get(task.key);
        } else {
            taskEntitity = new TaskEntitity(String.valueOf(++counter), task.title, task.description);
        }

        taskEntitity.copyFrom(task);

        callback.onSuccess(taskEntitity);
    }
}
