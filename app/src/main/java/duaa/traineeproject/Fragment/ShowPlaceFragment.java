package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import duaa.traineeproject.Adapter.OldTraineeAdapter;
import duaa.traineeproject.Adapter.PlaceAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class ShowPlaceFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    PlaceAdapter placeAdapter;
FontTextViewRegular title ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_place, container, false);

        bindView();
        title.setText(getString(R.string.placePart));
        ArrayList<PlaceModel> arrayList = new ArrayList<>();
        arrayList.add(new PlaceModel("مستشفى الأقصى", "20"));
        arrayList.add(new PlaceModel("مستشفى الشفا", "50"));
        arrayList.add(new PlaceModel("مستشفى القدس", "20"));
        arrayList.add(new PlaceModel("مستشفى الاندونيسي", "20"));
        arrayList.add(new PlaceModel("مستشفى الأقصى", "20"));
        arrayList.add(new PlaceModel("مستشفى الأقصى", "20"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        placeAdapter = new PlaceAdapter(getActivity(), arrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new UniversityAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
            }
        });
        recyclerView.setAdapter(placeAdapter);
        placeAdapter.notifyDataSetChanged();


        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.recyclerview);
        title = view.findViewById(R.id.title);
    }
}
