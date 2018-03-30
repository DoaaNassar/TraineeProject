package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;


public class AddSpecificationFragment extends Fragment {

    View view;
    List<Specification> specificationList;
    RecyclerView recyclerView;
    SpecAdapter specAdapter;
    FontButtonRegular addSpec;
    FontEditTextViewRegular spec;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specificationList = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_specification, container, false);
        bindView();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        specAdapter = new SpecAdapter(getActivity(), specificationList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new SpecAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
                specificationList.remove(position);
                specAdapter.notifyItemRemoved(position);
                specAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(specAdapter);
        specAdapter.notifyDataSetChanged();
        addSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("duaaa","duaaaa");

                specificationList.add(new Specification(spec.getText().toString(), spec.getText().toString()));
                specAdapter.notifyDataSetChanged();

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.specificationList);
        addSpec = view.findViewById(R.id.addSpec);
        spec = view.findViewById(R.id.specText);

    }

}
