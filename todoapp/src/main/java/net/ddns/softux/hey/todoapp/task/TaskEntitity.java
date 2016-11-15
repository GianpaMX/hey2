package net.ddns.softux.hey.todoapp.task;

public class TaskEntitity {
    public String key;
    public String title;
    public String description;
    public boolean checked;

    public TaskEntitity() {

    }

    public TaskEntitity(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }

    public TaskEntitity(Task task) {
        copyFrom(task);
    }

    public void copyFrom(Task task) {
        this.key = task.key;
        this.title = task.title;
        this.description = task.description;
        this.checked = task.checked;
    }

    public Task toModel() {
        return new Task(key, title, description);
    }
}
