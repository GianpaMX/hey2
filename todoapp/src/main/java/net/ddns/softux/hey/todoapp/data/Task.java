package net.ddns.softux.hey.todoapp.data;

public class Task {
    public static final int ACTIVE = 0;
    public static final int REMOVED = 1;

    public String key;
    public String title;
    public String description;
    public boolean checked;
    public int status;

    public Task() {
        this(null, null, null);
    }

    public Task(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.checked = false;
        this.status = ACTIVE;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Builder {
        private Task task;

        public Builder() {
            this.task = new Task();
        }

        public Builder key(String key) {
            task.key = key;
            return this;
        }

        public Builder title(String title) {
            task.title = title;
            return this;
        }

        public Builder description(String description) {
            task.description = description;
            return this;
        }

        public Builder checked(boolean checked) {
            task.checked = checked;
            return this;
        }

        public Builder status(int status) {
            task.status = status;
            return this;
        }

        public Task build() {
            return task;
        }
    }
}
