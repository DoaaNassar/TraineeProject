package duaa.traineeproject.Fragment;

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
import duaa.traineeproject.JavaObject.TraineeObject;
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

        ArrayList <TraineeObject> arrayList = new ArrayList<>();
        arrayList.add(new TraineeObject("دعاء  نصار","","","","","",""));
        arrayList.add(new TraineeObject("اسراء بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("باسمة بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("حمزة بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("توبة بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("منة بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("دعاء بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("ريم بسام نصار","","","","","",""));
        arrayList.add(new TraineeObject("امنة الصالحي","","","","","",""));
        arrayList.add(new TraineeObject(" بسام نصار","","","","","",""));

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