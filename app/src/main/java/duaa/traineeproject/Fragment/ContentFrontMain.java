package duaa.traineeproject.Fragment;

import android.os.Bundle;
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

import duaa.traineeproject.Page.TraineeViewPager;
import duaa.traineeproject.Page.UniversityViewPager;
import duaa.traineeproject.R;


public class ContentFrontMain extends Fragment {

    Fragment fragment =null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_content_front_main, container, false);

        ////Trainee ImageButton
        ImageView addTraineeBtn = view.findViewById(R.id.addTraineeImage);
        ImageView showNowTraineeBtn = view.findViewById(R.id.showTraineeImage);
        ImageView showOldTraineeBtn = view.findViewById(R.id.showDeletedTraineeImage);

        ////Trainer ImageButton
        ImageView addTrainerBtn = view.findViewById(R.id.addTrainerImage);
        ImageView showNowTrainerBtn = view.findViewById(R.id.showTrainerImage);
        ImageView showOldTrainerBtn = view.findViewById(R.id.showDeletedTrainerImage);

        ////User ImageButton
        ImageView addUserBtn = view.findViewById(R.id.addUserImage);
        ImageView showNowUserBtn = view.findViewById(R.id.showUserImage);

        ////Place ImageButton

        ImageView addPlaceBtn = view.findViewById(R.id.addTraineeImage);
        ImageView showPlaceBtn = view.findViewById(R.id.showTraineeImage);

        ////UniversityImageButton

        ImageView showUniversityBtn = view.findViewById(R.id.addUniversityImage);
        ImageView addSpecBtn = view.findViewById(R.id.specImage);
        ImageView addFacultyBtn = view.findViewById(R.id.facultyImage);

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

        addSpecBtn.setOnClickListener(new View.OnClickListener() {
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

}
