package net.ddns.softux.hey.androidapp.tasklist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.BaseFragment;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.List;

public class TaskListFragment extends BaseFragment<TaskListFragment.TaskListFragmentContainerListener> implements TaskListView {
    private TaskListAdapter taskListAdapter;

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskListAdapter = newTaskListAdapter(containerListener);
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

    @NonNull
    protected TaskListAdapter newTaskListAdapter(TaskListFragmentContainerListener taskListFragmentContainerListener) {
        return new TaskListAdapter(taskListFragmentContainerListener);
    }

    @Override
    public void loadTaskList(List<TaskViewModel> taskViewModelList) {
        taskListAdapter.swapTaskViewModelList(taskViewModelList);
    }

    @Override
    public void addTask(TaskViewModel taskViewModel) {
        taskListAdapter.addTaskViewModel(taskViewModel);
    }

    @Override
    protected boolean isContainerListener() {
        return getActivity() instanceof TaskListFragmentContainerListener;
    }

    public interface TaskListFragmentContainerListener {
        boolean onLongClickTask(TaskViewModel taskViewModel);

        void onCheckedTask(TaskViewModel taskViewModel);

        void onUncheckedTask(TaskViewModel taskViewModel);
    }
}
