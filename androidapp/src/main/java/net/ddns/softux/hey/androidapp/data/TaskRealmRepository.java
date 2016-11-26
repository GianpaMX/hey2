package net.ddns.softux.hey.androidapp.data;

import android.support.annotation.NonNull;

import net.ddns.softux.hey.androidapp.data.realm.TaskRealmObject;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class TaskRealmRepository implements TaskRepository, RealmChangeListener<RealmResults<TaskRealmObject>> {
    private Realm realm;
    private Observer observer;

    private RealmResults<TaskRealmObject> tasks;

    public TaskRealmRepository(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void findByKey(String key, Callback<Task> callback) {
        TaskRealmObject result = realm.where(TaskRealmObject.class).equalTo("key", key).findFirst();
        callback.onSuccess(result.toModel());
    }

    @Override
    public void findAll(Callback<Collection<Task>> callback) {
        if (tasks == null) tasks = realm.where(TaskRealmObject.class).findAll();

        Collection<Task> result = getTasksFromRealmResults(tasks);

        callback.onSuccess(result);
    }

    @NonNull
    private Collection<Task> getTasksFromRealmResults(RealmResults<TaskRealmObject> realmResults) {
        Collection<Task> result = new ArrayList<>();
        for (TaskRealmObject taskRealmObject : realmResults) {
            result.add(taskRealmObject.toModel());
        }
        return result;
    }

    @Override
    public void persist(final Task task, final Callback<Task> callback) {
        final TaskRealmObject taskRealmObject = new TaskRealmObject(task);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (taskRealmObject.key == null) taskRealmObject.key = getNextKey(realm);
                realm.copyToRealmOrUpdate(taskRealmObject);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess(taskRealmObject.toModel());
            }
        });
    }

    private int getNextKey(Realm realm) {
        Number number = realm.where(TaskRealmObject.class).max("key");
        return 1 + (number == null ? 0 : number.intValue());
    }


    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;

        if (observer != null) {
            if (tasks == null) tasks = realm.where(TaskRealmObject.class).findAll();
            tasks.addChangeListener(this);
        } else if (observer == null && tasks != null) {
            tasks.removeChangeListener(this);
        }
    }

    @Override
    public Observer getObserver() {
        return observer;
    }

    @Override
    public void onChange(RealmResults<TaskRealmObject> results) {
        if (observer != null) {
            observer.onTaskListChanged(getTasksFromRealmResults(results));
        }
    }
}
