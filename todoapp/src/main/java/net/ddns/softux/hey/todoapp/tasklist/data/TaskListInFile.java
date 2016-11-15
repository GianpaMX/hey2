package net.ddns.softux.hey.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.utils.ObjectFile;

import java.io.IOException;
import java.util.Map;

public class TaskListInFile extends TaskListInMemory {
    private final ObjectFile objectFile;

    public TaskListInFile(ObjectFile objectFile) {
        this.objectFile = objectFile;
    }

    @Override
    public void loadTaskList() {
        try {
            tasks = (Map<String, TaskEntitity>) objectFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            onTaskListGatewayListener.onTaskListLoadError(e, this);
        }
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
