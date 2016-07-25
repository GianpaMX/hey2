package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;

import java.util.Collection;

/**
 * Created by juan on 3/07/16.
 */
public interface TaskListGateway {
    void setOnTaskListGatewayListener(OnTaskListGatewayListener onTaskListGatewayListener);
    void loadTaskList();
    void saveTaskList();

    interface OnTaskListGatewayListener {
        void onTaskListLoad(Collection<TaskEntitity> taskEntitities, TaskListGateway taskListGateway);
        void onTaskListSaved(TaskListGateway taskListGateway);
        void onTaskAdded(TaskEntitity taskEntitity, TaskListGateway taskListGateway);

        void onTaskListSaveError(Exception e, TaskListGateway taskListGateway);
    }
}
