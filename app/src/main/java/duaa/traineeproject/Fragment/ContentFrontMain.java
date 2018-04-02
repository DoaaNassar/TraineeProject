package duaa.traineeproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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


public class ContentFrontMain extends Fragment {

    Fragment fragment =null;
    LinearLayout addTraineeBtn,showNowTraineeBtn,showOldTraineeBtn,
            addTrainerBtn,showNowTrainerBtn,showOldTrainerBtn,addPlaceBtn,showPlaceBtn,showUniversityBtn,
            facultyBtn,addFacultyBtn,university,profileLayout;

    View view ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_content_front_main, container, false);






        final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        addTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(2,fragment);

            }});

        showNowTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(1,fragment);

            }});


        showOldTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TraineeViewPager();
                Fragment(0,fragment);

            }});

        showUniversityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(0,fragment);

            }});

        facultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(1,fragment);

            }});

        addFacultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(2,fragment);

            }});

        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new UniversityViewPager();
                Fragment(3,fragment);
            }
        });

        addPlaceBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlaceFragmentPager();
                Fragment(0,fragment);
            }
        });

        showPlaceBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PlaceFragmentPager();
                Fragment(1,fragment);
            }
        });

        addTrainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TrainerFragment();
                Fragment(0,fragment);

            }
        });

        showNowTraineeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new TrainerFragment();
                Fragment(1,fragment);
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


    public void Fragment(int  numberOfPage , Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("numberViewPager",numberOfPage);
        fragment.setArguments(bundle);
        fragmentTransaction.addToBackStack("").replace(R.id.containerLayout,fragment).commit();

    }

    public void bindView (){

        ////Trainee ImageButton
        addTraineeBtn = view.findViewById(R.id.addTrainee);
         showNowTraineeBtn = view.findViewById(R.id.showTrainee);
         showOldTraineeBtn = view.findViewById(R.id.showDeletedTrainee);

        ////Trainer ImageButton
         addTrainerBtn = view.findViewById(R.id.addTrainer);
         showNowTrainerBtn = view.findViewById(R.id.showTrainer);
         showOldTrainerBtn = view.findViewById(R.id.showDeletedTrainer);

        ////Place ImageButton

         addPlaceBtn = view.findViewById(R.id.addPlace);
         showPlaceBtn = view.findViewById(R.id.place);

        ////UniversityImageButton

         showUniversityBtn = view.findViewById(R.id.addUniversity);
         addFacultyBtn = view.findViewById(R.id.addFaculty);
         facultyBtn = view.findViewById(R.id.faculty);
         university = view.findViewById(R.id.university);

         profileLayout  = view.findViewById(R.id.profile);
    }

}
