package net.ddns.softux.hey.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.tasklist.OnTaskListLoadListener;

import java.util.List;

public class TaskListPresenter implements OnTaskListLoadListener {
    public TaskListView view;

    @Override
    public void onTaskListLoad(List<Task> tasks) {
        TaskListViewModel taskViewModelList = new TaskListViewModel();
        taskViewModelList.addAll(tasks);
        view.loadTaskList(taskViewModelList);
    }

    @Override
    public void onTaskAdded(Task task) {
        view.addTask(new TaskViewModel(task));
    }
}
