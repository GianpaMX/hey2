package net.ddns.softux.hey.todoapp.savetask;

/**
 * Created by juan on 30/06/16.
 */
public class Task {
    public String key;
    public String title;
    public String description;

    public Task(String title, String description) {
        this(null, title, description);
    }

    public Task(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }
}