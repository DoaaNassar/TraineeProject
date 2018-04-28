package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterAddPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerPartPlace;
import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.PartObject;
import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.Model.AddFacultyModel;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class AddPlaceFragment extends Fragment {
    View view;
    FontTextViewRegular title;
    ArrayList<PartObject> partList;
    Typeface face;
    RecyclerView recyclerView;
    AdapterAddPlace adapterAddPlace;
    ImageView addPlace , search;
    FontEditTextViewRegular placeName ,  email , address , phone , mobile , number;
    FontButtonRegular save ;
    FrameLayout loadingLayout;
    Dialog dialog ;




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
        search.setVisibility(View.GONE);
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

                if (!TextUtils.isEmpty(placeName.getText().toString())&&!TextUtils.isEmpty(number.getText().toString())) {
                    partList.add(new PartObject(placeName.getText().toString(),number.getText().toString()));
                    adapterAddPlace.notifyDataSetChanged();
                } else {
                    Alarm("أضف القسم");
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()){

                    AddItem(new Place(placeName.getText().toString(),partList,email.getText().toString()
                            ,mobile.getText().toString(),phone.getText().toString(),address.getText().toString()));
                }else {

                }
            }
        });

        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
        placeName = view.findViewById(R.id.textPart);
        addPlace = view.findViewById(R.id.addPart);
        save = view.findViewById(R.id.save);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        mobile  = view.findViewById(R.id.mobile);
        number = view.findViewById(R.id.number);
        search = getActivity().findViewById(R.id.search);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);



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
        showDialog(getActivity());
        new UserAPI().AddPlace(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;

                if (responseItem.isStatus()) {
                }
                Alarm(responseItem.getMessage());
                dialog.hide();
                loadingLayout.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getResources().getString(R.string.noAdd));
                    loadingLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null) {
                    Alarm(getResources().getString(R.string.noInternet));

                }
                loadingLayout.setVisibility(View.GONE);

            }
        });
    }

    public boolean Validate() {

        if (TextUtils.isEmpty(placeName.getText().toString())){
            return false;
        } else if (partList.size()==0) {
            return false;
        }
        return true;
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.diii);
        dialog.setCancelable(false);

        dialog.show();

    }

}
