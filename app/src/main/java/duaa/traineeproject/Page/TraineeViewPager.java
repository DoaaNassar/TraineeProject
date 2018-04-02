package duaa.traineeproject.Page;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duaa.traineeproject.Fragment.AddTrainees;
import duaa.traineeproject.Fragment.OldTraineeFragment;
import duaa.traineeproject.Fragment.ShowTrainees;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

@SuppressLint("ResourceAsColor")

public class TraineeViewPager extends Fragment {
    View view;
    FontTextViewRegular addTrainee,showTrainee,oldTrainee;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_trainee_view_pager, container, false);
        bindView();

        addTrainee();

        Fragment con = new AddTrainees();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.containerTrainee, con).commit();

        addTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddTrainees();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerTrainee, con).commit();
                addTrainee();


            }
        });

        showTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new ShowTrainees();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerTrainee, con).commit();
                showTrainee();

            }
        });

        oldTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new OldTraineeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerTrainee, con).commit();
                oldTrainee();

            }
        });
        return view;
    }


    public void bindView (){
        addTrainee = view.findViewById(R.id.addTrainee);
        showTrainee =view.findViewById(R.id.showTrainee);
        oldTrainee =view.findViewById(R.id.showDeletedTrainee);


    }

    public void addTrainee(){

        addTrainee.setTextColor(getResources().getColor(R.color.colorPrimary));
        showTrainee.setTextColor(getResources().getColor(R.color.silver));
        oldTrainee.setTextColor(getResources().getColor(R.color.silver));


    }

    public void showTrainee(){

        addTrainee.setTextColor(getResources().getColor(R.color.silver));
        showTrainee.setTextColor(getResources().getColor(R.color.colorPrimary));
        oldTrainee.setTextColor(getResources().getColor(R.color.silver));

    }

    public void oldTrainee(){

        addTrainee.setTextColor(getResources().getColor(R.color.silver));
        showTrainee.setTextColor(getResources().getColor(R.color.silver));
        oldTrainee.setTextColor(getResources().getColor(R.color.colorPrimary));

    }

}