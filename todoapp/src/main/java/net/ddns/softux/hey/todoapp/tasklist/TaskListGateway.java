package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;

import java.util.Collection;

/**
 * Created by juan on 3/07/16.
 */
public interface TaskListGateway {
    void setOnTaskListGatewayListener(OnTaskListGatewayListener onTaskListGatewayListener);
    void loadTaskList();

    interface OnTaskListGatewayListener {
        void onTaskListLoad(Collection<TaskEntitity> taskEntitities);
        void onTaskAdded(TaskEntitity taskEntitity);
    }
}
