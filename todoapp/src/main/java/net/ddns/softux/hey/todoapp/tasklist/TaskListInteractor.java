package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;

public class TaskListInteractor implements TaskListUseCase, TaskRepository.Observer {
    protected TaskRepository taskRepository;
    protected Collection<Task> activeTasks;
    private Observer observer;

    public TaskListInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskRepository.setObserver(this);
    }

    @Override
    public void start(final Observer observer) {
        this.observer = observer;
        if (activeTasks != null) {
            observer.onTaskListChanged(activeTasks);
        }

        taskRepository.findAll(new TaskRepository.Callback<Collection<Task>>() {
            @Override
            public void onSuccess(Collection<Task> result) {
                onTaskListChanged(result);
            }

            @Override
            public void onFailure(Throwable error) {

            }
        });
    }

    @Override
    public void stop() {
        this.observer = null;
    }

    @Override
    public void onTaskListChanged(Collection<Task> tasks) {
        if (this.activeTasks == null) {
            this.activeTasks = new ArrayList<>();
        } else {
            this.activeTasks.clear();
        }

        for (Task task : tasks) {
            if (task.status != Task.REMOVED) this.activeTasks.add(task);
        }

        if (observer != null) observer.onTaskListChanged(this.activeTasks);
    }
}
