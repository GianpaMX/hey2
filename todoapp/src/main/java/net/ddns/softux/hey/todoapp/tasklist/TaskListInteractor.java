package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by juan on 3/07/16.
 */

public class TaskListInteractor implements TaskListUseCase, TaskListGateway.OnTaskListGatewayListener {
    protected TaskListGateway taskListGateway;
    private OnTaskListLoadListener onTaskListLoadListener;

    public TaskListInteractor(TaskListGateway taskListGateway) {
        this.taskListGateway = taskListGateway;
    }

    @Override
    public void start(OnTaskListLoadListener onTaskListLoadListener) {
        this.onTaskListLoadListener = onTaskListLoadListener;
        taskListGateway.loadTaskList(this);
    }

    @Override
    public void onTaskListLoad(Collection<TaskEntitity> taskEntitities) {
        List<Task> taskList = new ArrayList<>();
        for(TaskEntitity entitity : taskEntitities) {
            taskList.add(entitity.toModel());
        }
        onTaskListLoadListener.onTaskListLoad(taskList);
    }
}
