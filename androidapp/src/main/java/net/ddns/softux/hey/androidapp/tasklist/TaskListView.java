package net.ddns.softux.hey.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.List;

public interface TaskListView {
    void loadTaskList(List<TaskViewModel> taskViewModelList);
}
