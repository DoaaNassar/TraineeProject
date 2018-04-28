package duaa.traineeproject.Fragment;

import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.ImageView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.OldTrainerAdapter;
import duaa.traineeproject.Adapter.PlaceAdapter;
import duaa.traineeproject.Adapter.TrainerAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.OldTrainerListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.Model.TrainerListModel;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class OldTrainerFragment extends Fragment {

    View view;
    FontTextViewRegular title;
    RecyclerView recyclerView;
    OldTrainerAdapter oldTrainerAdapter;
    ArrayList<Trainer> arrayList;
    Typeface face;
    ImageView search ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_trainer, container, false);
        bindView();
        title.setText(getString(R.string.trainerPart));

        trainer();
        search.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
        search = getActivity().findViewById(R.id.search);

    }

    public void trainer() {

        new UserAPI().getAllNowTrainer("0",new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                TrainerListModel response = (TrainerListModel) result;

//                if (responseCategories.isStatus()) {
//                loading.setVisibility(View.GONE);
                arrayList.clear();
                arrayList.addAll(response.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                oldTrainerAdapter = new OldTrainerAdapter(getActivity(), arrayList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new OldTrainerAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                recyclerView.setAdapter(oldTrainerAdapter);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                oldTrainerAdapter.notifyDataSetChanged();

            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    loading.setVisibility(View.GONE);

                    if (getActivity() != null)
                        Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {
//                loading.setVisibility(View.GONE);

            }

            @Override
            public void OnError(String message) {
//                loading.setVisibility(View.GONE);
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
