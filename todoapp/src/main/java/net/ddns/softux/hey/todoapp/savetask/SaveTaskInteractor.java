package net.ddns.softux.hey.todoapp.savetask;

import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;

public class SaveTaskInteractor implements SaveTaskUseCase {
    protected SaveTaskGateway saveTaskGateway;

    public SaveTaskInteractor(SaveTaskGateway saveTaskGateway) {
        this.saveTaskGateway = saveTaskGateway;
    }

    @Override
    public void save(Task task, final OnSaveTaskListener onSaveTaskListener) {
        saveTaskGateway.save(task, new SaveTaskGatewayCallback() {
            @Override
            public void onSuccess(TaskEntitity savedTask) {
                onSaveTaskListener.onSavedTask(savedTask.toModel());
            }
        });
    }

    @Override
    public void check(final Task task, final OnSaveTaskListener onSaveTaskListener) {
        task.checked = true;
        saveTaskGateway.save(task, new SaveTaskGatewayCallback() {
            @Override
            public void onSuccess(TaskEntitity savedTask) {
                if (onSaveTaskListener != null)
                    onSaveTaskListener.onSavedTask(savedTask.toModel());
            }
        });
    }

    @Override
    public void uncheck(Task task, final OnSaveTaskListener onSaveTaskListener) {
        task.checked = false;
        saveTaskGateway.save(task, new SaveTaskGatewayCallback() {
            @Override
            public void onSuccess(TaskEntitity savedTask) {
                if (onSaveTaskListener != null)
                    onSaveTaskListener.onSavedTask(savedTask.toModel());
            }
        });
    }
}
