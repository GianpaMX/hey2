package net.ddns.softux.hey.todoapp.data;

import java.util.Collection;

public interface TaskRepository {
    void findByKey(String key, Callback<Task> callback);

    void findAll(Callback<Collection<Task>> result);

    void persist(Task task, Callback<Task> result);

    interface Callback<T> {
        void onSuccess(T result);

        void onFailure(Throwable error);
    }

    void setObserver(Observer observer);

    Observer getObserver();

    interface Observer {
        void onTaskListChanged(Collection<Task> tasks);
    }
}
