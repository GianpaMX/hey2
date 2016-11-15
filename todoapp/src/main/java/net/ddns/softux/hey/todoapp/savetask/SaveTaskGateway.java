package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;

public interface SaveTaskGateway extends TaskListGateway {
    void save(Task task, SaveTaskGatewayCallback callback);
}
