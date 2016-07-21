package net.ddns.softux.hey.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;

import java.util.Collection;

/**
 * Created by juan on 20/07/16.
 */
public class TaskListRepository implements TaskListGateway, TaskListGateway.OnTaskListGatewayListener {
    private final TaskListGateway[] gateways;
    private OnTaskListGatewayListener onTaskListGatewayListener;

    public TaskListRepository(TaskListGateway... gateways) {
        this.gateways = gateways;
        for(TaskListGateway l : gateways) {
            l.setOnTaskListGatewayListener(this);
        }
    }

    @Override
    public void setOnTaskListGatewayListener(OnTaskListGatewayListener onTaskListGatewayListener) {
        this.onTaskListGatewayListener = onTaskListGatewayListener;
    }

    @Override
    public void loadTaskList() {
        for(TaskListGateway l : gateways) {
            l.loadTaskList();
        }
    }

    @Override
    public void onTaskListLoad(Collection<TaskEntitity> taskEntitities) {
        onTaskListGatewayListener.onTaskListLoad(taskEntitities);
    }

    @Override
    public void onTaskAdded(TaskEntitity taskEntitity) {
        onTaskListGatewayListener.onTaskAdded(taskEntitity);
    }
}
