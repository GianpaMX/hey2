package net.ddns.softux.hey.androidapp.tasklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.List;

import javax.inject.Inject;

public class TaskListFragment extends Fragment implements TaskListView {

    @Inject
    public TaskListAdapter taskListAdapter;

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject(this);
    }

    private void inject(TaskListFragment taskListFragment) {
        TaskListActivity activity = (TaskListActivity) getActivity();
        activity.getTaskListActivityModule().inject(taskListFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_fragment, container, false);

        RecyclerView taskListRecyclerView = (RecyclerView) view.findViewById(R.id.task_list);
        taskListRecyclerView.setHasFixedSize(true);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskListRecyclerView.setAdapter(taskListAdapter);

        return view;
    }

    @Override
    public void loadTaskList(List<TaskViewModel> taskViewModelList) {
        taskListAdapter.swapTaskViewModelList(taskViewModelList);
    }

    @Override
    public void addTask(TaskViewModel taskViewModel) {
        taskListAdapter.addTaskViewModel(taskViewModel);
    }

    public interface TaskListFragmentContainerListener {
        boolean onLongClickTask(TaskViewModel taskViewModel);

        void onCheckedTask(TaskViewModel taskViewModel);

        void onUncheckedTask(TaskViewModel taskViewModel);
    }
}
