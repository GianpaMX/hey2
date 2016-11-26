package net.ddns.softux.hey.androidapp.tasklist;

import android.os.Parcelable;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;

import java.util.Collection;

public class TaskListPresenter implements TaskListUseCase.Observer {
    private TaskListUseCase taskListUseCase;
    private SaveTaskUseCase saveTaskUseCase;
    public TaskListView view;

    public TaskListPresenter(TaskListUseCase taskListUseCase, SaveTaskUseCase saveTaskUseCase) {
        this.taskListUseCase = taskListUseCase;
        this.saveTaskUseCase = saveTaskUseCase;
    }

    public void start() {
        taskListUseCase.start(this);
    }

    public void stop() {
        taskListUseCase.stop();
    }

    @Override
    public void onTaskListChanged(Collection<Task> tasks) {
        if (view == null) return;

        TaskListViewModel taskViewModelList = new TaskListViewModel(tasks);
        view.loadTaskList(taskViewModelList);
    }

    public void check(TaskViewModel taskViewModel) {
        saveTaskUseCase.check(taskViewModel.toTask(), null);
    }

    public void uncheck(TaskViewModel taskViewModel) {
        saveTaskUseCase.uncheck(taskViewModel.toTask(), null);
    }

    public void restore(TaskViewModel taskViewModel) {
        saveTaskUseCase.restore(taskViewModel.toTask(), null);
    }
}
