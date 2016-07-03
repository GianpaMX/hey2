package net.ddns.softux.hey.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.List;

/**
 * Created by juan on 2/07/16.
 */
public interface TaskListView {
    void loadTaskList(List<TaskViewModel> taskViewModelList);
    void addTask(TaskViewModel taskViewModel);
}
