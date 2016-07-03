package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;

/**
 * Created by juan on 30/06/16.
 */
public interface SaveTaskGatewayCallback {
    void onSuccess(TaskEntitity savedTask);
}
