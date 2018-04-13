package duaa.traineeproject.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.TrainerAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.Model.TrainerListModel;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class ShowTrainerFragment extends Fragment {
    View view ;
    TrainerAdapter trainerAdapter ;
    RecyclerView recyclerView ;
    ArrayList<Trainer> trainerList ;
    FontTextViewRegular title ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainerList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_trainer, container, false);
        bindView();
        title.setText(getString(R.string.trainerPart));
        ArrayList<Trainer> arrayList = new ArrayList<>();
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        trainerAdapter = new TrainerAdapter(getActivity(), arrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new UniversityAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
            }
        });
        recyclerView.setAdapter(trainerAdapter);
        trainerAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return view ;
    }

    public void bindView(){
        recyclerView = view.findViewById(R.id.listShow);
        title = getActivity().findViewById(R.id.title);

    }

    public void trainer() {

        new UserAPI().getAllNowTrainer(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                TrainerListModel response = (TrainerListModel) result;
                Log.d("ddddd", "ffff");


//                if (responseCategories.isStatus()) {
                trainerList.clear();
                trainerList.addAll(response.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                trainerAdapter = new TrainerAdapter(getActivity(),trainerList ,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new UniversityAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                recyclerView.setAdapter(trainerAdapter);
//                    specAdapter.notifyDataSetChanged();


            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    loading.setVisibility(View.GONE);

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
//                loading.setVisibility(View.GONE);


            }

            @Override
            public void OnError(String message) {
//                loading.setVisibility(View.GONE);


//                Alerter.create(getActivity())
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();


            }
        });


    }
}
