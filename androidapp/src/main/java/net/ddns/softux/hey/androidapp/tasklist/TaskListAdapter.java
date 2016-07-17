package net.ddns.softux.hey.androidapp.tasklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 2/07/16.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private View.OnLongClickListener onLongClickListener;
    private List<TaskViewModel> taskViewModelList;
    private TaskListFragment.TaskListFragmentContainerListener taskListFragmentContainerListener;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = getOnCheckedChangeListener();

    public TaskListAdapter(TaskListFragment.TaskListFragmentContainerListener taskListFragmentContainerListener) {
        taskViewModelList = new ArrayList<>();
        this.taskListFragmentContainerListener = taskListFragmentContainerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false), new ViewHolder.TaskListItemListener() {
            @Override
            public View.OnLongClickListener getOnLongClickListener() {
                return TaskListAdapter.this.getOnLongClickListener();
            }

            @Override
            public CompoundButton.OnCheckedChangeListener getOnCheckChangeListener() {
                return TaskListAdapter.this.getOnCheckedChangeListener();
            }
        });
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

    @NonNull
    public View.OnLongClickListener getOnLongClickListener() {
        if (onLongClickListener == null) {
            onLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return TaskListAdapter.this.taskListFragmentContainerListener.onLongClickTask((TaskViewModel) view.getTag());
                }
            };
        }
        return onLongClickListener;
    }

    @NonNull
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        if (onCheckedChangeListener == null) {
            onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    View view = (View) compoundButton.getParent();
                    if (checked) {
                        TaskListAdapter.this.taskListFragmentContainerListener.onCheckedTask((TaskViewModel) view.getTag());
                    }
                }
            };
        }

        return onCheckedChangeListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final CheckBox checkbox;

        public ViewHolder(View itemView, TaskListItemListener listener) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);

            itemView.setOnLongClickListener(listener.getOnLongClickListener());
            checkbox.setOnCheckedChangeListener(listener.getOnCheckChangeListener());
        }

        public void bind(TaskViewModel taskViewModel) {
            itemView.setTag(taskViewModel);

            title.setText(taskViewModel.title);
            checkbox.setChecked(taskViewModel.checked);
        }

        public interface TaskListItemListener {
            View.OnLongClickListener getOnLongClickListener();

            CompoundButton.OnCheckedChangeListener getOnCheckChangeListener();
        }
    }
}
