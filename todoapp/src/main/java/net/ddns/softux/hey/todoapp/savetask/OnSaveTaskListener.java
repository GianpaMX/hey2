package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.Task;

public interface OnSaveTaskListener {
    void onSavedTask(Task task);
}
