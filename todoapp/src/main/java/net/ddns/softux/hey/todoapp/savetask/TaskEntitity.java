package net.ddns.softux.hey.todoapp.savetask;

/**
 * Created by juan on 30/06/16.
 */

public class TaskEntitity {
    public String key;
    public String title;
    public String description;

    public TaskEntitity() {

    }

    public TaskEntitity(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }

    public void copyFrom(Task task) {
        this.key = task.key;
        this.title = task.title;
        this.description = task.description;
    }

    public Task toModel() {
        return new Task(key, title, description);
    }
}
