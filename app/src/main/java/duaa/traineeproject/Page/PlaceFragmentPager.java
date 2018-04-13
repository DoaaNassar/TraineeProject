package duaa.traineeproject.Page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duaa.traineeproject.Fragment.AddPlaceFragment;
import duaa.traineeproject.Fragment.AddTrainees;
import duaa.traineeproject.Fragment.ShowPlaceFragment;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class PlaceFragmentPager extends Fragment {

    View view;
    FontTextViewRegular addPlace, place ,title;
    int number ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getArguments().getInt("numberViewPager");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place_fragment_pager, container, false);


        bindView();

        if (number==1){
            showPlace();
            Fragment con = new ShowPlaceFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerPlace, con).commit();

        }else {
            addPlace();
            Fragment con = new AddPlaceFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.containerPlace, con).commit();
        }
        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddPlaceFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerPlace, con).commit();
                addPlace();
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment con = new ShowPlaceFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerPlace, con).commit();
                showPlace();
            }
        });


        return view;
    }

    public void bindView() {

        addPlace = view.findViewById(R.id.addPlace);
        place = view.findViewById(R.id.place);
        title = getActivity().findViewById(R.id.title);
    }
    public void addPlace(){

        addPlace.setTextColor(getResources().getColor(R.color.colorPrimary));
        place.setTextColor(getResources().getColor(R.color.silver));


    }

    public void showPlace(){

        addPlace.setTextColor(getResources().getColor(R.color.silver));
        place.setTextColor(getResources().getColor(R.color.colorPrimary));


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
                    }
                    else {
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
