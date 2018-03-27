package duaa.traineeproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

import duaa.traineeproject.Adapter.ListTraineeAdapter;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.R;


public class ShowTrainees extends android.app.Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_trainees, container, false);

        ArrayList <TrainerObject> arrayList = new ArrayList<>();
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
        final ListTraineeAdapter adapter = new ListTraineeAdapter(getActivity(), arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        CheckBox checkBox =view.findViewById(R.id.checkAll);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                adapter.toggleSelectAll();
                adapter.notifyDataSetChanged();

                }
                else {
                    adapter.toggleSelectAll();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}