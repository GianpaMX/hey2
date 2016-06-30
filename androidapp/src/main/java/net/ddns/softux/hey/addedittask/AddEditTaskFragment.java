package net.ddns.softux.hey.addedittask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.databinding.AddEditTaskFragmentBinding;

public class AddEditTaskFragment extends Fragment {
    private static final String TASK = "TASK";

    protected TaskViewModel taskViewModel;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskViewModel = new TaskViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_edit_task_fragment, container, false);
        AddEditTaskFragmentBinding binding = AddEditTaskFragmentBinding.bind(view);
        binding.setTaskViewModel(taskViewModel);
        return view;
    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }
}
