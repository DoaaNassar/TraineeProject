package duaa.traineeproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import java.util.Timer;
import java.util.TimerTask;

import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);

        if (!TextUtils.isEmpty(ApplicationController.getInstance().token())){

            Intent refresh = new Intent(this, NavigationMenuActivity.class);
            startActivity(refresh);


        }else {
            TimerTask task =new TimerTask() {
                @Override
                public void run() {

                    finish();
                    Intent intent =new Intent(MainActivity.this,Login.class);
                    startActivity(intent);

                }
            };
            Timer opening = new Timer();
            opening.schedule(task,4000);
        }





    }
}
