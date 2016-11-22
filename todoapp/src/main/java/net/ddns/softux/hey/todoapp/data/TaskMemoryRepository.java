package net.ddns.softux.hey.todoapp.data;

import java.util.Collection;
import java.util.Map;

public class TaskMemoryRepository implements TaskRepository {
    private Map<String, Task> tasks;
    int counter;

    private Observer observer;

    public TaskMemoryRepository(Map<String, Task> tasks, int counter) {
        this.tasks = tasks;
        this.counter = counter;
    }

    @Override
    public void findByKey(String key, Callback<Task> callback) {
        callback.onSuccess(tasks.containsKey(key) ? tasks.get(key) : null);
    }

    @Override
    public void findAll(Callback<Collection<Task>> result) {
        result.onSuccess(tasks.values());
    }

    @Override
    public void persist(Task task, Callback<Task> result) {
        if (task.key == null) task.key = String.valueOf(++counter);

        tasks.put(task.key, task);

        if (observer != null) observer.onTaskListChanged(tasks.values());

        result.onSuccess(task);
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public Observer getObserver() {
        return observer;
    }
}
