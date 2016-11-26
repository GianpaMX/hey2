package net.ddns.softux.hey.androidapp.task;

import android.os.Parcel;
import android.os.Parcelable;

import net.ddns.softux.hey.todoapp.data.Task;

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
    public boolean checked;
    public int status;

    public TaskViewModel() {
        this(null, null, null);
    }

    public TaskViewModel(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.checked = false;
        this.status = Task.ACTIVE;
    }

    protected TaskViewModel(Parcel in) {
        key = in.readString();
        title = in.readString();
        description = in.readString();
        checked = in.readInt() == 1 ? true : false;
        status = in.readInt();
    }

    public TaskViewModel(Task task) {
        this.key = task.key;
        this.title = task.title != null ? task.title.trim() : null;
        this.description = task.description != null ? task.description.trim() : null;
        this.checked = task.checked;
        this.status = task.status;
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
        dest.writeInt(checked ? 1 : 0);
        dest.writeInt(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        TaskViewModel that = (TaskViewModel) o;

        return hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (status);
        return result;
    }

    public Task toTask() {
        return Task.Builder()
                .key(key)
                .title(title != null ? title.trim() : null)
                .description(description != null ? description.trim() : null)
                .checked(checked)
                .status(status)
                .build();
    }
}
