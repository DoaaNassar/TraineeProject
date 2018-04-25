package duaa.traineeproject.Fragment;

import android.graphics.Typeface;
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
import android.widget.LinearLayout;

import com.tapadoo.alerter.Alerter;
import com.wang.avi.AVLoadingIndicatorView;

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

import static duaa.traineeproject.Constants.FONTS_APP;


public class ShowTrainerFragment extends Fragment {
    View view;
    TrainerAdapter trainerAdapter;
    RecyclerView recyclerView;
    ArrayList<Trainer> trainerList;
    FontTextViewRegular title;
    LinearLayout loading;
    Typeface face ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainerList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_trainer, container, false);
        bindView();
        title.setText(getResources().getString(R.string.trainerPart));
        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.recyclerview);
        title = getActivity().findViewById(R.id.title);
        loading = view.findViewById(R.id.loading);


    }

    public void trainer() {

        new UserAPI().getAllNowTrainer(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                TrainerListModel response = (TrainerListModel) result;

//                if (responseCategories.isStatus()) {
                loading.setVisibility(View.GONE);
                trainerList.clear();
                trainerList.addAll(response.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                trainerAdapter = new TrainerAdapter(getActivity(), trainerList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new TrainerAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                recyclerView.setAdapter(trainerAdapter);
                trainerAdapter.notifyDataSetChanged();

            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    loading.setVisibility(View.GONE);

                    if (getActivity() != null)
                      Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.GONE);

            }

            @Override
            public void OnError(String message) {
                loading.setVisibility(View.GONE);
                if (getActivity() != null)
                    Alarm(getResources().getString(R.string.noInternet));

            }
        });


    }

    public void Alarm(String message) {
        Alerter.create(getActivity())
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }
}
