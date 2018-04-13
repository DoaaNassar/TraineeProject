package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

import duaa.traineeproject.Adapter.ListTraineeAdapter;
import duaa.traineeproject.Adapter.OldTraineeAdapter;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class OldTraineeFragment extends Fragment {

View view ;
FontTextViewRegular title ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_old_trainee, container, false);
        bindView();
        title.setText(getString(R.string.traineePart));

        ArrayList<TrainerObject> arrayList = new ArrayList<>();
        arrayList.add(new TrainerObject("دعاء  نصار","","","","","",""));
        arrayList.add(new TrainerObject("اسراء بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("باسمة بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("حمزة بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("توبة بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("منة بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("دعاء بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("ريم بسام نصار","","","","","",""));
        arrayList.add(new TrainerObject("امنة الصالحي","","","","","",""));
        arrayList.add(new TrainerObject(" بسام نصار","","","","","",""));

        ListView listView =view.findViewById(R.id.listUserShow);
        final OldTraineeAdapter adapter = new OldTraineeAdapter(getActivity(), arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);



        // Inflate the layout for this fragment
        return view ;
    }

    public void bindView (){
        title = getActivity().findViewById(R.id.title);
    }


}
