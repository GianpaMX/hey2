package net.ddns.softux.hey.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.ddns.softux.hey.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
