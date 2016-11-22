package net.ddns.softux.hey.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;

import java.util.ArrayList;
import java.util.Collection;

public class TaskListViewModel extends ArrayList<TaskViewModel> {
    public TaskListViewModel(int initialCapacity) {
        super(initialCapacity);
    }

    public TaskListViewModel() {
    }

    public TaskListViewModel(Collection<? extends Task> tasks) {
        for (Task task : tasks) {
            add(new TaskViewModel(task));
        }
    }
}
