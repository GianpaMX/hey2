package net.ddns.softux.hey.androidapp.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.addedittask.BaseActivity;

/**
 * Created by juan on 2/07/16.
 */
public class TaskListActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);

        setupToolbar();

        ImageButton newTaskButton = (ImageButton) findViewById(R.id.new_task_button);
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, AddEditTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
