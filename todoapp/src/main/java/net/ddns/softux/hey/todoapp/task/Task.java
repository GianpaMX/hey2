package net.ddns.softux.hey.todoapp.task;

/**
 * Created by juan on 30/06/16.
 */
public class Task {
    public String key;
    public String title;
    public String description;
    public boolean checked;

    public Task() {
        this(null, null, null);
    }

    public Task(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
