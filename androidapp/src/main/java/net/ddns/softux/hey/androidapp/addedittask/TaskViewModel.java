package net.ddns.softux.hey.androidapp.addedittask;

import android.os.Parcel;
import android.os.Parcelable;

import net.ddns.softux.hey.todoapp.savetask.Task;

/**
 * Created by juan on 30/06/16.
 */
public class TaskViewModel implements Parcelable {
    public static final Creator<TaskViewModel> CREATOR = new Creator<TaskViewModel>() {
        @Override
        public TaskViewModel createFromParcel(Parcel in) {
            return new TaskViewModel(in);
        }

        @Override
        public TaskViewModel[] newArray(int size) {
            return new TaskViewModel[size];
        }
    };
    public String key;
    public String title;
    public String description;

    public TaskViewModel() {
        this(null, null, null);
    }

    public TaskViewModel(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }

    protected TaskViewModel(Parcel in) {
        key = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public TaskViewModel(Task task) {
        this.key = task.key;
        this.title = task.title;
        this.description = task.description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(title);
        dest.writeString(description);
    }

    public Task toTask() {
        return new Task(key, title, description);
    }
}
