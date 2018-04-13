package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class addUniversityFragment extends Fragment {

    View view;
    FontTextViewRegular title ;

    public static addUniversityFragment newInstance(String param1, String param2) {
        addUniversityFragment fragment = new addUniversityFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_university2, container, false);


        bindView();
        title.setText(getString(R.string.universityPart));
        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);


    }

}
