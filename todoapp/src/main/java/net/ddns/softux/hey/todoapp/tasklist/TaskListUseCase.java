package net.ddns.softux.hey.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.data.Task;

import java.util.Collection;

public interface TaskListUseCase {
    void start(Observer observer);
    void stop();

    interface Observer {
        void onTaskListChanged(Collection<Task> tasks);
    }
}
