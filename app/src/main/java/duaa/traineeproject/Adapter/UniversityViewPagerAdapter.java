package duaa.traineeproject.Adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import duaa.traineeproject.Fragment.AddTrainees;
import duaa.traineeproject.Fragment.ShowTrainees;
import duaa.traineeproject.R;

public class UniversityViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int FRAGMENT_1 = 0;
    private final int FRAGMENT_2 = 1;
    private final int FRAGMENT_3 = 2;
    private final int FRAGMENT_4 = 3;


    private final int COUNT = 4;
    private final Context context;

    public UniversityViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
//            case FRAGMENT_1:
//                fragment = new ShowTrainees();
//                break;
//            case FRAGMENT_2:
//                fragment = new ShowTrainees();
//                break;
//            case FRAGMENT_3:
//                fragment = new AddTrainees();
//                break;
//
//            case FRAGMENT_4:
//                fragment = new AddTrainees();
//                break;


        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String fragment = null;
        switch (position) {
            case FRAGMENT_1:
                fragment = context.getString(R.string.showOldTrainee);
                break;

            case FRAGMENT_2:
                fragment = context.getString(R.string.showTrainee);
                break;

            case FRAGMENT_3:
                fragment = context.getString(R.string.addTrainee);
                break;

            case FRAGMENT_4:
                fragment = context.getString(R.string.addTrainee);
                break;


        }
        return fragment;
    }
}