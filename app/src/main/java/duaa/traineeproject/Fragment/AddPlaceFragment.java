package duaa.traineeproject.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterAddPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerPartPlace;
import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.Model.AddFacultyModel;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class AddPlaceFragment extends Fragment {
    View view;
    FontTextViewRegular title;
    ArrayList<String> partList;
    Typeface face;
    RecyclerView recyclerView;
    AdapterAddPlace adapterAddPlace;
    ImageView addPlace;
    FontEditTextViewRegular place;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        partList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_place, container, false);
        bindView();
        title.setText("قسم الأماكن");
        bindView();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterAddPlace = new AdapterAddPlace(getActivity(), partList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new AdapterAddPlace.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
                partList.remove(position);
                adapterAddPlace.notifyItemRemoved(position);
                adapterAddPlace.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapterAddPlace);
        adapterAddPlace.notifyDataSetChanged();
        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(place.getText().toString())) {
                    partList.add(place.getText().toString());
                    adapterAddPlace.notifyDataSetChanged();
                } else {
                    Alarm("أضف القسم");
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
        place = view.findViewById(R.id.textPart);
        addPlace = view.findViewById(R.id.addPart);
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

    public void AddItem(final Place item) {

        isBack = true;
        new UserAPI().AddPlace(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseTrue responseItem = (ResponseTrue) result;

//                if (responseItem.isStatus()) {
                if (getActivity() != null)
                    Alarm(responseItem.getMessage());

//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm("لا يوجد انترنت");

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null) {
                    Alarm("لا يوجد انترنت");

                }
            }
        });
    }

}
