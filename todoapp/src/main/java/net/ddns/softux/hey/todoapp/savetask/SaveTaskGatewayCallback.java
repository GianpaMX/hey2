package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;

public interface SaveTaskGatewayCallback {
    void onSuccess(TaskEntitity savedTask);
}
