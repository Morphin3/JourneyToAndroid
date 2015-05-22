package com.taoism.journeytoandroid.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.SystemUtils;

/**
 * Created by Morphin3 on 5/5/15.
 */
public class ActionBarDemoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_action_bar_demo);

        setTitle("首页");
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.demo_action_bar_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_1:
                SystemUtils.showToast(this,"一一");
                return true;
            case R.id.action_2:
                SystemUtils.showToast(this,"二二");
                return true;
            case R.id.action_3:
                SystemUtils.showToast(this,"三三");
                return true;
            case R.id.action_4:
                SystemUtils.showToast(this,"四四");
                return true;
            case R.id.action_5:
                SystemUtils.showToast(this,"五五2");
                return true;
            case R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if(NavUtils.shouldUpRecreateTask(this, upIntent)){
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                }else{
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this,upIntent);
                }
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
