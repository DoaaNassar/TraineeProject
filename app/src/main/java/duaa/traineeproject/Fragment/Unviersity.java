package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;


public class Unviersity extends android.app.Fragment {
    RecyclerView recyclerView;
    View view;
    UniversityAdapter universityAdapter;
    ArrayList<University> universityList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        universityList =new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_unviersity, container, false);
        bindView();
        universityList.add(new University(1,"دعاء","دعاء","دعاء"));
        universityList.add(new University(1,"دعاء","دعاء","دعاء"));
        universityList.add(new University(1,"دعاء","دعاء","دعاء"));


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        universityAdapter = new UniversityAdapter(getActivity(), universityList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new UniversityAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
//                universityList.remove(position);
//                universityAdapter.notifyItemRemoved(position);
//                universityAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(universityAdapter);
        universityAdapter.notifyDataSetChanged();

        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.listUniversityShow);

    }
}
