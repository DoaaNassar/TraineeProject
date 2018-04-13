package duaa.traineeproject.Page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duaa.traineeproject.Fragment.AddFacultyFragment;
import duaa.traineeproject.Fragment.AddSpecificationFragment;
import duaa.traineeproject.Fragment.AddUniversity;
import duaa.traineeproject.Fragment.Unviersity;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class UniversityViewPager extends Fragment {


    FontTextViewRegular addUniversity, addFaculty, addSpecification, university;
    View view;
    int number = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getArguments().getInt("numberViewPager");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_university_view_pager, container, false);
        bindView();

        if (number == 3) {
            university();
            Fragment con = new Unviersity();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerUniversity, con).commit();

        } else if (number == 2) {

            Fragment con = new AddSpecificationFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerUniversity, con).commit();
            addSpec();

        } else if (number == 1) {
            Fragment con = new AddFacultyFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerUniversity, con).commit();
            addFaculty();
        } else {
            addUniversity();
            Fragment con = new AddUniversity();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerUniversity, con).commit();

        }
        addUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddUniversity();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerUniversity, con).commit();
                addUniversity();


            }
        });

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddFacultyFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerUniversity, con).commit();
                addFaculty();

            }
        });


        addSpecification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddSpecificationFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerUniversity, con).commit();
                addSpec();

            }
        });


        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new Unviersity();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerUniversity, con).commit();
                university();

            }
        });

        return view;
    }


    public void bindView() {
        addUniversity = view.findViewById(R.id.addUniversity);
        addFaculty = view.findViewById(R.id.addFaculty);
        addSpecification = view.findViewById(R.id.addSpecialization);
        university = view.findViewById(R.id.university);


    }

    public void addUniversity() {

        addUniversity.setTextColor(getResources().getColor(R.color.colorPrimary));
        addFaculty.setTextColor(getResources().getColor(R.color.silver));
        addSpecification.setTextColor(getResources().getColor(R.color.silver));
        university.setTextColor(getResources().getColor(R.color.silver));


    }

    public void addFaculty() {

        addUniversity.setTextColor(getResources().getColor(R.color.silver));
        addFaculty.setTextColor(getResources().getColor(R.color.colorPrimary));
        addSpecification.setTextColor(getResources().getColor(R.color.silver));
        university.setTextColor(getResources().getColor(R.color.silver));


    }

    public void addSpec() {

        addUniversity.setTextColor(getResources().getColor(R.color.silver));
        addFaculty.setTextColor(getResources().getColor(R.color.silver));
        addSpecification.setTextColor(getResources().getColor(R.color.colorPrimary));
        university.setTextColor(getResources().getColor(R.color.silver));


    }

    public void university() {

        addUniversity.setTextColor(getResources().getColor(R.color.silver));
        addFaculty.setTextColor(getResources().getColor(R.color.silver));
        addSpecification.setTextColor(getResources().getColor(R.color.silver));
        university.setTextColor(getResources().getColor(R.color.colorPrimary));


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
                    if (isBack) {
                        getActivity().getSupportFragmentManager().popBackStack("DuaaBassamNassar", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } else {
//
                        int x = getFragmentManager().getBackStackEntryCount()-1;
                        for (int i = 0; i < x; i++) {
                            getFragmentManager().popBackStack();
                        }
                    }
                    return true;

                }

                return false;
            }
        });
    }
}