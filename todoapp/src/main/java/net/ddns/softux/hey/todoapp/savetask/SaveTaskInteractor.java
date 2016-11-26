package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskRepository;

public class SaveTaskInteractor implements SaveTaskUseCase {
    protected TaskRepository taskRepository;

    public SaveTaskInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void save(Task task, final Callback callback) {
        taskRepository.persist(task, createRepositoryCallback(callback));
    }

    @Override
    public void check(final Task task, final Callback callback) {
        task.checked = true;
        taskRepository.persist(task, createRepositoryCallback(callback));
    }

    @Override
    public void uncheck(Task task, final Callback callback) {
        task.checked = false;
        taskRepository.persist(task, createRepositoryCallback(callback));
    }

    @Override
    public void remove(Task task, Callback callback) {
        task.status = Task.REMOVED;
        taskRepository.persist(task, createRepositoryCallback(callback));
    }

    @Override
    public void restore(Task task, Callback callback) {
        task.status = Task.ACTIVE;
        taskRepository.persist(task, createRepositoryCallback(callback));
    }

    private TaskRepository.Callback<Task> createRepositoryCallback(final Callback callback) {
        return new TaskRepository.Callback<Task>() {
            @Override
            public void onSuccess(Task result) {
                if (callback != null) callback.onSuccess(result);
            }

            @Override
            public void onFailure(Throwable error) {
                if (callback != null) callback.onFailure(error);
            }
        };
    }
}
