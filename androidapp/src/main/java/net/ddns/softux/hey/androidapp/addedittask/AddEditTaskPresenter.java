package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

public class AddEditTaskPresenter {
    private SaveTaskUseCase saveTaskUseCase;
    protected AddEditTaskView view;

    public AddEditTaskPresenter(SaveTaskUseCase saveTaskUseCase) {
        this.saveTaskUseCase = saveTaskUseCase;
    }

    public void save(TaskViewModel taskViewModel) {
        saveTaskUseCase.save(taskViewModel.toTask(), new SaveTaskUseCase.Callback() {
            public void onSuccess(Task task) {
                if (view == null) return;

                view.setTaskViewModel(new TaskViewModel(task));
                view.showSuccess();
            }

            @Override
            public void onFailure(Throwable error) {

            }
        });
    }

    public void remove(TaskViewModel taskViewModel) {
        saveTaskUseCase.remove(taskViewModel.toTask(), new SaveTaskUseCase.Callback() {
            @Override
            public void onSuccess(Task task) {
                view.taskRemoved(new TaskViewModel(task));
            }

            @Override
            public void onFailure(Throwable error) {

            }
        });
    }

    public AddEditTaskView getView() {
        return view;
    }

    public void setView(AddEditTaskView view) {
        this.view = view;
    }
}
