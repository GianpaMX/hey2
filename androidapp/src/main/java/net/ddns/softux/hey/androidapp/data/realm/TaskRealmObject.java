package net.ddns.softux.hey.androidapp.data.realm;

import net.ddns.softux.hey.todoapp.data.Task;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class TaskRealmObject extends RealmObject {
    @PrimaryKey
    public Integer key;

    public String title;
    public String description;
    public boolean checked;
    public int status;

    public TaskRealmObject() {
    }

    public TaskRealmObject(Task task) {
        key = task.key == null ? null : new Integer(task.key);
        title = task.title;
        description = task.description;
        checked = task.checked;
        status = task.status;
    }

    public Task toModel() {
        return Task.Builder()
                .key(key == null ? null : key.toString())
                .title(title)
                .description(description)
                .checked(checked)
                .status(status)
                .build();
    }
}
