package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.savetask.OnSaveTaskListener;
import net.ddns.softux.hey.todoapp.task.Task;

public class AddEditTaskPresenter implements OnSaveTaskListener {

    protected AddEditTaskView view;

    public AddEditTaskPresenter() {
    }

    public AddEditTaskPresenter(AddEditTaskView view) {
        this.view = view;
    }

    public AddEditTaskView getView() {
        return view;
    }

    public void setView(AddEditTaskView view) {
        this.view = view;
    }

    @Override
    public void onSavedTask(Task task) {
        view.setTaskViewModel(new TaskViewModel(task));
        view.showSuccess();
    }
}
