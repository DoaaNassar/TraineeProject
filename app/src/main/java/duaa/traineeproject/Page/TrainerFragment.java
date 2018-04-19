package duaa.traineeproject.Page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duaa.traineeproject.Fragment.AddTrainerFragment;
import duaa.traineeproject.Fragment.OldTraineeFragment;
import duaa.traineeproject.Fragment.OldTrainerFragment;
import duaa.traineeproject.Fragment.ShowTrainerFragment;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class TrainerFragment extends Fragment {

    View view;
    FontTextViewRegular addTrainer, showTrainer, oldTrainer;
    int number;
    public static boolean isBack = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getArguments().getInt("numberViewPager");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trainer, container, false);
        bindView();

        if (number == 0) {
            Fragment(new AddTrainerFragment());
            addTrainer();
        } else if (number == 1) {
            Fragment(new ShowTrainerFragment());
            showTrainer();
        } else {
            Fragment(new OldTrainerFragment());
            oldTrainer();
        }

        addTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment(new AddTrainerFragment());
                addTrainer();
            }
        });

        showTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment(new ShowTrainerFragment());
                showTrainer();
            }
        });

        oldTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment(new OldTrainerFragment());
                oldTrainer();
            }
        });


        return view;
    }

    public void bindView() {
        addTrainer = view.findViewById(R.id.addTrainer);
        showTrainer = view.findViewById(R.id.showTrainer);
        oldTrainer = view.findViewById(R.id.showDeletedTrainer);
    }

    public void addTrainer() {

        addTrainer.setTextColor(getResources().getColor(R.color.colorPrimary));
        showTrainer.setTextColor(getResources().getColor(R.color.silver));
        oldTrainer.setTextColor(getResources().getColor(R.color.silver));


    }

    public void showTrainer() {

        addTrainer.setTextColor(getResources().getColor(R.color.silver));
        showTrainer.setTextColor(getResources().getColor(R.color.colorPrimary));
        oldTrainer.setTextColor(getResources().getColor(R.color.silver));

    }

    public void oldTrainer() {

        addTrainer.setTextColor(getResources().getColor(R.color.silver));
        showTrainer.setTextColor(getResources().getColor(R.color.silver));
        oldTrainer.setTextColor(getResources().getColor(R.color.colorPrimary));

    }

    public void Fragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerTrainer, fragment).commit();

    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.d("isBack",isBack+"");
                    if (isBack) {
                        getActivity().getSupportFragmentManager().popBackStack("DuaaBassamNassar", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } else {
//
                        int x = getFragmentManager().getBackStackEntryCount() - 1;
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
