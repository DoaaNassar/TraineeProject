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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.FacultyAdapter;
import duaa.traineeproject.Adapter.OldTraineeAdapter;
import duaa.traineeproject.Adapter.PlaceAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.PlaceListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class ShowPlaceFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    PlaceAdapter placeAdapter;
    FontTextViewRegular title;
    ArrayList<PlaceModel> arrayList;
    Typeface face;
    LinearLayout loading, noInternet;
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
        view = inflater.inflate(R.layout.fragment_show_place, container, false);

        bindView();
        title.setText(getResources().getString(R.string.placePart));

        getAllplace();
        search.setVisibility(View.VISIBLE);


        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.recyclerview);
        title = getActivity().findViewById(R.id.title);
        loading = view.findViewById(R.id.loading);
        search = getActivity().findViewById(R.id.search);


    }

    public void getAllplace() {

        new UserAPI().getPlace( new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                PlaceListModel placeListModel = (PlaceListModel) result;

//                if (responseCategories.isStatus()) {
                loading.setVisibility(View.GONE);

                arrayList.clear();
                arrayList.addAll(placeListModel.getResult());
                placeAdapter = new PlaceAdapter(getActivity(), arrayList, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                    }
                }, new PlaceAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {
                    }
                });
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(placeAdapter);
                placeAdapter.notifyDataSetChanged();

                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alarm(getResources().getString(R.string.noAdd));
                    loading.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.GONE);

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null)
                    Alarm(getResources().getString(R.string.noInternet));
                loading.setVisibility(View.GONE);


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
