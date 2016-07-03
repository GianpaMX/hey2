package net.ddns.softux.hey.androidapp.addedittask;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.databinding.AddEditTaskFragmentBinding;

public class AddEditTaskFragment extends Fragment implements AddEditTaskView {
    public static final String TASK_VIEW_MODEL = "TASK_VIEW_MODEL";

    protected AddEditTaskFragmentContainerListener containerListener;

    protected TaskViewModel taskViewModel;
    private AddEditTaskFragmentBinding binding;

    public static AddEditTaskFragment newInstance(TaskViewModel taskViewModel) {
        Bundle args = new Bundle();
        args.putParcelable(TASK_VIEW_MODEL, taskViewModel);

        AddEditTaskFragment addEditTaskFragment = new AddEditTaskFragment();
        addEditTaskFragment.setArguments(args);
        return addEditTaskFragment;
    }

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof AddEditTaskFragmentContainerListener) {
            containerListener = (AddEditTaskFragmentContainerListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        containerListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            taskViewModel = savedInstanceState.getParcelable(TASK_VIEW_MODEL);
        } else if (getArguments() != null && getArguments().containsKey(TASK_VIEW_MODEL)) {
            taskViewModel = getArguments().getParcelable(TASK_VIEW_MODEL);
        } else {
            taskViewModel = new TaskViewModel();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(TASK_VIEW_MODEL, taskViewModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_edit_task_fragment, container, false);
        binding = AddEditTaskFragmentBinding.bind(view);
        binding.setTaskViewModel(taskViewModel);
        return view;
    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }

    @Override
    public void setTaskViewModel(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;
        binding.setTaskViewModel(taskViewModel);
    }

    @Override
    public void showSuccess() {
        if(containerListener != null) {
            containerListener.onTaskSavedSuccessfully(taskViewModel);
        }
    }

    public interface AddEditTaskFragmentContainerListener {
        void onTaskSavedSuccessfully(TaskViewModel taskViewModel);
    }
}
