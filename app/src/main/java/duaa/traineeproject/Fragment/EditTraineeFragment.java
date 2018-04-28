package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import duaa.traineeproject.R;


public class EditTraineeFragment extends Fragment {
    View view;
    ImageView search ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_trainee, container, false);

        return view;


    }
    public void bindView (){
        search = getActivity().findViewById(R.id.search);


    }


}
