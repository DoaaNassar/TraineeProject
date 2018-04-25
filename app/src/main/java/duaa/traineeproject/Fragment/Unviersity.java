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
import android.widget.LinearLayout;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.ShowUniversityAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class Unviersity extends Fragment {
    RecyclerView universityRecyclerView;
    View view;
    UniversityAdapter universityAdapter;
    ArrayList<University> universityList;
    UniversityAdapter showUniversityAdapter;
    LinearLayout loading, noInternet;
    FontTextViewRegular title ,universityTab;


    Typeface face ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        universityList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_unviersity, container, false);
        bindView();
        title.setText(getResources().getString(R.string.universityPart));
        university();

        return view;
    }

    public void bindView() {
        universityRecyclerView = view.findViewById(R.id.listUniversityShow);
        loading = view.findViewById(R.id.loading);
        title = getActivity().findViewById(R.id.title);
        universityTab = getActivity().findViewById(R.id.university);


    }

    public void university() {
        universityTab.setEnabled(false);
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;

                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);


//                if (responseCategories.isStatus()) {
                universityList.clear();
                universityList.addAll(responseCategories.getResult());
                universityRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                showUniversityAdapter = new UniversityAdapter(getActivity(), universityList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new UniversityAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                universityRecyclerView.setAdapter(showUniversityAdapter);
                showUniversityAdapter.notifyDataSetChanged();


            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    loading.setVisibility(View.GONE);
                    universityTab.setEnabled(true);

                    if (getActivity() != null)
                        Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);



            }

            @Override
            public void OnError(String message) {
                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);
                if(getActivity()!=null)
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
