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


public class AddPlaceFragment extends Fragment {
    View view;
    FontTextViewRegular title;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_place, container, false);
        bindView();
        title.setText("قسم الأماكن");

        // Inflate the layout for this fragment
        return view ;
    }

    public void bindView(){
        title = getActivity().findViewById(R.id.title);

    }
}
