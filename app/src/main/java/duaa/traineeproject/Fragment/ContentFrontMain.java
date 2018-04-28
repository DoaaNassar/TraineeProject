package duaa.traineeproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import duaa.traineeproject.Activity.EditTrainee;
import duaa.traineeproject.Page.PlaceFragmentPager;
import duaa.traineeproject.Page.TraineeViewPager;
import duaa.traineeproject.Page.TrainerFragment;
import duaa.traineeproject.Page.UniversityViewPager;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class ContentFrontMain extends Fragment {

    Fragment fragment =null;

    FontTextViewRegular title;
    View view ;
    ImageView search ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_content_front_main, container, false);
       title = getActivity().findViewById(R.id.title);
       title.setText(getString(R.string.app_name));
       bindView();
        search.setVisibility(View.GONE);

        ////Trainee layout
        LinearLayout  addTraineeBtn = view.findViewById(R.id.addTrainee);
        LinearLayout  showNowTraineeBtn = view.findViewById(R.id.showTrainee);
        LinearLayout  showOldTraineeBtn = view.findViewById(R.id.showDeletedTrainee);

        ////Trainer layout
        LinearLayout  addTrainerBtn = view.findViewById(R.id.addTrainer);
        LinearLayout  showNowTrainerBtn = view.findViewById(R.id.showTrainer);
        LinearLayout  showOldTrainerBtn = view.findViewById(R.id.showDeletedTrainer);

        ////Place layout

        LinearLayout addPlaceBtn = view.findViewById(R.id.addPlace);
        LinearLayout showPlaceBtn = view.findViewById(R.id.place);

        ////University layout

        LinearLayout showUniversityBtn = view.findViewById(R.id.addUniversity);
        LinearLayout addFacultyBtn = view.findViewById(R.id.addFaculty);
        LinearLayout facultyBtn = view.findViewById(R.id.faculty);
        LinearLayout university = view.findViewById(R.id.university);

        LinearLayout profileLayout  = view.findViewById(R.id.profile);

        final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        addTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(2,fragment ,"TraineeViewPager");

            }});

        showNowTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(1,fragment,"TraineeViewPager");

            }});


        showOldTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(0,fragment,"TraineeViewPager");

            }});

        showUniversityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(0,fragment,"UniversityViewPager");

            }});

        facultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(1,fragment,"UniversityViewPager");

            }});

        addFacultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(2,fragment,"UniversityViewPager");

            }});

        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(3,fragment,"UniversityViewPager");
            }
        });

        addPlaceBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlaceFragmentPager();
                Fragment(0,fragment,"PlaceFragmentPager");
            }
        });

        showPlaceBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlaceFragmentPager();
                Fragment(1,fragment,"PlaceFragmentPager");
            }
        });

        addTrainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TrainerFragment();
                Fragment(0,fragment,"TrainerFragment");

            }
        });

        showNowTrainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TrainerFragment();
                Fragment(1,fragment,"TrainerFragment");
            }
        });

        showOldTrainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TrainerFragment();
                Fragment(2,fragment,"TrainerFragment");
            }
        });


        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditTrainee.class);
                startActivity(intent);
            }
        });

        return view;
    }


    public void Fragment(int  numberOfPage , Fragment fragment ,String  name){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("numberViewPager",numberOfPage);
        fragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null).replace(R.id.containerLayout,fragment).commit();

    }

    public void bindView (){

        title = getActivity().findViewById(R.id.title);
        search = getActivity().findViewById(R.id.search);


    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK ) {
                    getActivity().finish();


                    return true;

                }

                return false;
            }
        });
    }
}
