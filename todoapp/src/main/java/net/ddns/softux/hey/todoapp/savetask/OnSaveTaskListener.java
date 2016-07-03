package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.Task;

/**
 * Created by juan on 30/06/16.
 */
public interface OnSaveTaskListener {
    void onSavedTask(Task task);
}
