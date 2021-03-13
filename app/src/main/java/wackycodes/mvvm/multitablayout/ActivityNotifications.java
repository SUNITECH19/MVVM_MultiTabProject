package wackycodes.mvvm.multitablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import wackycodes.mvvm.multitablayout.databinding.ActivityNotificationsBinding;

public class ActivityNotifications extends AppCompatActivity {

    private ActivityNotificationsBinding activityNotificationsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNotificationsBinding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return false;
    }



}