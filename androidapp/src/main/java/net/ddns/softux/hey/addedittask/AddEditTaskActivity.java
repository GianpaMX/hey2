package net.ddns.softux.hey.addedittask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

import javax.inject.Inject;

public class AddEditTaskActivity extends AppCompatActivity {

    @Inject
    public SaveTaskUseCase saveTaskUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task_activity);

        ((AndroidApp)getApplication()).getAndroidAppComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.task_fragment, AddEditTaskFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_task_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveTaskUseCase.save(getTaskViewModel().toTask());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private TaskViewModel getTaskViewModel() {
        return ((AddEditTaskFragment)getSupportFragmentManager().findFragmentById(R.id.task_fragment)).getTaskViewModel();
    }
}
