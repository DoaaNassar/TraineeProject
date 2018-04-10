package duaa.traineeproject.Fragment;

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

import duaa.traineeproject.Adapter.ShowUniversityAdapter;
import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;


public class AddFacultyFragment extends Fragment {
    View view;
    SpecAdapter specAdapter;
    ShowUniversityAdapter showUniversityAdapter ;
    List<Specification> specificationList;
    ArrayList<University>unviersityList;
    FontButtonRegular addSpec;
    RecyclerView recyclerView,universityRecyclerView;
    FontEditTextViewRegular spec ;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specificationList =new ArrayList<>();
        unviersityList =new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_add_faculty, container, false);
        bindView();


//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        specAdapter = new SpecAdapter(getActivity(), specificationList, new CustomItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
//            }
//        }, new SpecAdapter.MyRecyclerViewListener() {
//            @Override
//            public void RemoveImage(View v, int position) {
//                specificationList.remove(position);
//                specAdapter.notifyItemRemoved(position);
//                specAdapter.notifyDataSetChanged();
//            }
//        });
//
//        recyclerView.setAdapter(specAdapter);
//        specAdapter.notifyDataSetChanged();
//        addSpec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                specificationList.add(new Specification(spec.getText().toString(),spec.getText().toString()));
//                specAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        university();


        return view ;
    }


    public void bindView (){
        addSpec = view.findViewById(R.id.addSpec);
        recyclerView =view.findViewById(R.id.specificationList);
//        universityRecyclerView=view.findViewById(R.id.universityList);
        spec =view.findViewById(R.id.textSpec);

    }


    public void university() {
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;
                Log.d("ddddd","ffff");

//                if (responseCategories.isStatus()) {
                unviersityList.clear();
                unviersityList.addAll(responseCategories.getResult());
                universityRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                showUniversityAdapter = new ShowUniversityAdapter(getActivity(), unviersityList,
                        new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                    }
                }, new ShowUniversityAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                universityRecyclerView.setAdapter(showUniversityAdapter);
                specAdapter.notifyDataSetChanged();

//                    categoryAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    if (getActivity() != null)
//                        Alerter.create(getActivity())
//                                .setText(responseError.getMessage())
//                                .hideIcon()
//                                .setBackgroundColorRes(R.color.colorPrimary)
//                                .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {

//                Alerter.create(getActivity())
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();


            }
        });



    }



}
