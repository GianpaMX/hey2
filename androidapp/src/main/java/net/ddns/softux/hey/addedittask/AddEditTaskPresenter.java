package net.ddns.softux.hey.addedittask;

import net.ddns.softux.hey.todoapp.savetask.OnSaveTaskListener;
import net.ddns.softux.hey.todoapp.savetask.Task;

/**
 * Created by juan on 30/06/16.
 */

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
        view.showSuccess(new TaskViewModel(task));
    }
}
