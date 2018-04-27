package duaa.traineeproject.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.R;
import es.dmoral.toasty.Toasty;

import static duaa.traineeproject.Constants.FONTS_APP;

public class MainActivity extends AppCompatActivity {
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        face = Typeface.createFromAsset(MainActivity.this.getAssets(), FONTS_APP);

        Toasty.Config.getInstance()
                .tintIcon(true) // optional (apply textColor also to the icon)
                .setToastTypeface(face) // optional
                .setTextSize(12)// optional
                .apply();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(ApplicationController.getInstance().token())) {

                    MainActivity.this.finish();
                    Intent refresh = new Intent(MainActivity.this, NavigationMenuActivity.class);
                    startActivity(refresh);

                } else {
                    finish();
                    Intent intent = new Intent(MainActivity.this, NavigationMenuActivity.class);
                    startActivity(intent);
                }

            }
        };
        Timer opening = new Timer();
        opening.schedule(task, 4000);


    }
}
