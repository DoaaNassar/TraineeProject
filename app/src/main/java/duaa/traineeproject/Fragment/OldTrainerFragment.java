package duaa.traineeproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.OldTrainerAdapter;
import duaa.traineeproject.Adapter.PlaceAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.OldTrainerListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;


public class OldTrainerFragment extends Fragment {

    View view ;
    FontTextViewRegular title ;
    RecyclerView recyclerView ;
    OldTrainerAdapter oldTrainerAdapter ;
    ArrayList<Trainer> arrayList ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_trainer, container, false);
        bindView();
        title.setText(getString(R.string.trainerPart));

        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));
        arrayList.add(new Trainer(1,"مستشفى الأقصى", "20"));


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        oldTrainerAdapter = new OldTrainerAdapter(getActivity(), arrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new UniversityAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
            }
        });
        recyclerView.setAdapter(oldTrainerAdapter);
        oldTrainerAdapter.notifyDataSetChanged();


        // Inflate the layout for this fragment
        return view;
    }

    public void bindView(){
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
    }

    public void getOldTrainer() {

        new UserAPI().getAllOldTrainer(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                OldTrainerListModel responseTrainer = (OldTrainerListModel) result;
                Log.d("ddddd", "ffff");


//                if (responseCategories.isStatus()) {
                arrayList.clear();
                arrayList.addAll(responseTrainer.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                oldTrainerAdapter = new OldTrainerAdapter(getActivity(), arrayList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new UniversityAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                recyclerView.setAdapter(oldTrainerAdapter);
                oldTrainerAdapter.notifyDataSetChanged();


            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;

                    if (getActivity() != null)
                         Alerter.create(getActivity())
                        .setText(responseError.getMessage())
                            .hideIcon()
                            .setContentGravity(GravityCompat.END)
                            .setBackgroundColorRes(R.color.colorPrimary)
                            .show();
                }
            }

            @Override
            public void onFinish() {


            }

            @Override
            public void OnError(String message) {


                Alerter.create(getActivity())
                        .setText("لا يوجد انترنت")
                        .hideIcon()
                        .setContentGravity(GravityCompat.END)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .show();


            }
        });


    }


}
