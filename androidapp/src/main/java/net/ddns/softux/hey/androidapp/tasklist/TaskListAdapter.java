package net.ddns.softux.hey.androidapp.tasklist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 2/07/16.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private List<TaskViewModel> taskViewModelList;
    private TaskListFragment.TaskListFragmentContainerListener taskListFragmentContainerListener;

    public final View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            return TaskListAdapter.this.taskListFragmentContainerListener.onLongClickTask((TaskViewModel) view.getTag());
        }
    };

    public TaskListAdapter(TaskListFragment.TaskListFragmentContainerListener taskListFragmentContainerListener) {
        taskViewModelList = new ArrayList<>();
        this.taskListFragmentContainerListener = taskListFragmentContainerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false), onLongClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(taskViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskViewModelList.size();
    }

    public void swapTaskViewModelList(List<TaskViewModel> taskViewModelList) {
        if (!this.taskViewModelList.isEmpty()) {
            this.taskViewModelList.clear();
        }

        this.taskViewModelList.addAll(taskViewModelList);

        notifyDataSetChanged();
    }

    public void addTaskViewModel(TaskViewModel taskViewModel) {
        int position = taskViewModelList.size();
        taskViewModelList.add(taskViewModel);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public ViewHolder(View itemView, View.OnLongClickListener onLongClickListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnLongClickListener(onLongClickListener);
        }

        public void bind(TaskViewModel taskViewModel) {
            itemView.setTag(taskViewModel);
            title.setText(taskViewModel.title);
        }
    }
}
