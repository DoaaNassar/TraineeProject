package duaa.traineeproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Fragment.ContentFrontMain;
import duaa.traineeproject.Model.UserDataResponse;
import duaa.traineeproject.Model.showUserLogin;
import duaa.traineeproject.Page.PlaceFragmentPager;
import duaa.traineeproject.Page.TraineeViewPager;
import duaa.traineeproject.Page.TrainerFragment;
import duaa.traineeproject.Page.UniversityViewPager;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

public class NavigationMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    FontTextViewRegular userName ;
    FontTextViewRegular userRole ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.containerLayout, new ContentFrontMain()).commit();
        bindView();
        setData();

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //     setSupportActionBar(toolbar);
        LinearLayout trainee = findViewById(R.id.trainee);
        LinearLayout trainer = findViewById(R.id.trainer);
        LinearLayout university = findViewById(R.id.university);
        LinearLayout out = findViewById(R.id.out);
        LinearLayout place = findViewById(R.id.place);
        LinearLayout home = findViewById(R.id.home);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.END);

            }
        });

        trainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new TraineeViewPager();
                Fragment(fragment);
                drawer.closeDrawer(GravityCompat.END);

            }
        });

        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new TrainerFragment();
                Fragment(fragment);
                drawer.closeDrawer(GravityCompat.END);


            }
        });

        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new UniversityViewPager();
                Fragment(fragment);
                drawer.closeDrawer(GravityCompat.END);


            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlaceFragmentPager();
                Fragment(fragment);
                drawer.closeDrawer(GravityCompat.END);

            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ContentFrontMain();
                Fragment(fragment);
                drawer.closeDrawer(GravityCompat.END);

            }
        });


//        fragment = new TraineeViewPager();
//        Fragment(fragment);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//
//        if (id == R.id.trainee) {
//            fragment = new TraineeViewPager();
//            Fragment(fragment);
//        } else if (id == R.id.university) {
//            fragment = new UniversityViewPager();
//            Fragment(fragment);
//
//
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    public void bindView (){
        userName = findViewById(R.id.userName);
        userRole = findViewById(R.id.userRole);

    }
    public void Fragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("numberViewPager", 0);
        fragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null).replace(R.id.containerLayout, fragment).commit();

    }

//    @Override
//    public void onActivityResult (int requestCode, int resultCode, Intent data) {
//        super_.onActivityResult(requestCode, resultCode, data);
//        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
//            Log.d("ffffff",requestCode+"");
//            fragment.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    public void setData (){
        showUserLogin user = ApplicationController.getInstance().getLoginUser();
        userName.setText(user.getUser_name());
        userRole.setText(user.getRole_id()+"");
    }

}
