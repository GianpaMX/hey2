package net.ddns.softux.hey.todoapp.task;

/**
 * Created by juan on 30/06/16.
 */
public class Task {
    public String key;
    public String title;
    public String description;

    public Task() {
        this(null, null, null);
    }

    public Task(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }
}
