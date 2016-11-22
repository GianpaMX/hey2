package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskRepository;

import java.util.Collection;

public class TaskListInteractor implements TaskListUseCase, TaskRepository.Observer {
    protected TaskRepository taskRepository;
    protected Collection<Task> tasks;
    private Observer observer;

    public TaskListInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskRepository.setObserver(this);
    }

    @Override
    public void start(final Observer observer) {
        this.observer = observer;
        if (tasks != null) {
            observer.onTaskListChanged(tasks);
        }

        taskRepository.findAll(new TaskRepository.Callback<Collection<Task>>() {
            @Override
            public void onSuccess(Collection<Task> result) {
                tasks = result;

                if (observer != null) {
                    observer.onTaskListChanged(result);
                }
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
        if (observer == null) return;

        observer.onTaskListChanged(tasks);
    }
}
