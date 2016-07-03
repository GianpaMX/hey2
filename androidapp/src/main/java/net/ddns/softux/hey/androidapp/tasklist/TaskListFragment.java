package net.ddns.softux.hey.androidapp.tasklist;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * Created by juan on 2/07/16.
 */

public class TaskListFragment extends Fragment implements TaskListView {
    private TaskListAdapter taskListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_fragment, container, false);

        taskListAdapter = newTaskListAdapter();

        RecyclerView taskListRecyclerView = (RecyclerView) view.findViewById(R.id.task_list);
        taskListRecyclerView.setHasFixedSize(true);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskListRecyclerView.setAdapter(taskListAdapter);

        return view;
    }

    @NonNull
    protected TaskListAdapter newTaskListAdapter() {
        return new TaskListAdapter();
    }

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onTaskListLoad(List<TaskViewModel> taskViewModelList) {
        taskListAdapter.swapTaskViewModelList(taskViewModelList);
    }
}
