package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.data.Task;

public interface SaveTaskUseCase {
    void save(Task task, Callback callback);

    void check(Task task, Callback callback);

    void uncheck(Task task, Callback callback);

    void remove(Task task, Callback callback);

    void restore(Task task, Callback callback);

    interface Callback {
        void onSuccess(Task task);

        void onFailure(Throwable error);
    }
}
