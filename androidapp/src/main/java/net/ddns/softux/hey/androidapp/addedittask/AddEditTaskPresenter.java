package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

public class AddEditTaskPresenter implements SaveTaskUseCase.Callback {
    private SaveTaskUseCase saveTaskUseCase;
    protected AddEditTaskView view;

    public AddEditTaskPresenter(SaveTaskUseCase saveTaskUseCase) {
        this.saveTaskUseCase = saveTaskUseCase;
    }

    public void save(TaskViewModel taskViewModel) {
        saveTaskUseCase.save(taskViewModel.toTask(), this);
    }

    @Override
    public void onSuccess(Task task) {
        if (view == null) return;

        view.setTaskViewModel(new TaskViewModel(task));
        view.showSuccess();
    }

    @Override
    public void onFailure(Throwable error) {

    }

    public AddEditTaskView getView() {
        return view;
    }

    public void setView(AddEditTaskView view) {
        this.view = view;
    }
}
