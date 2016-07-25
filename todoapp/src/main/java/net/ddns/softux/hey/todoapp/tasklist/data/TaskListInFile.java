package net.ddns.softux.hey.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.utils.ObjectFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by juan on 24/07/16.
 */
public class TaskListInFile extends TaskListInMemory {
    private final ObjectFile objectFile;

    public TaskListInFile(ObjectFile objectFile) {
        this.objectFile = objectFile;
    }

    @Override
    public void loadTaskList() {
        tasks = (Map<String, TaskEntitity>) objectFile.readObject();
        super.loadTaskList();
    }

    @Override
    public void saveTaskList() {
        try {
            objectFile.writeObject(tasks);
        } catch (IOException e) {
            onTaskListGatewayListener.onTaskListSaveError(e, this);
        }

        onTaskListGatewayListener.onTaskListSaved(this);
    }
}
