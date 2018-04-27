package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Activity.EditTrainee;
import duaa.traineeproject.Activity.NavigationMenuActivity;
import duaa.traineeproject.Adapter.AdapterNewTrainee;
import duaa.traineeproject.Adapter.AdapterOldTrainee;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.FacultyAdapter;
import duaa.traineeproject.Adapter.ListTraineeAdapter;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.TraineeListModel;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class ShowTrainees extends Fragment {

    View view;
    FontTextViewRegular title;
    RecyclerView recyclerView;
    AdapterNewTrainee adapter;
    ArrayList<TraineeModel> arrayList;
    int positionList;
    Typeface face;
    LinearLayout loading;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        arrayList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_show_trainees, container, false);

        bindView();
        title.setText(getResources().getString(R.string.traineePart));
        ShowTrainee();

        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.listUserShow);
        loading = view.findViewById(R.id.loading);

    }

    public void showDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("هل أنت متاكد ؟");
        builder.setCancelable(false);
        builder.setPositiveButton("نعم",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        positionList = position;
                        DeleteTrainee(arrayList.get(position) + "");

                    }
                });
        builder.setNegativeButton("لا",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void DeleteTrainee(String training_id) {
        new UserAPI().DeleteTrainee(training_id, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                ResponseSuccess responseSuccess = (ResponseSuccess) result;
                arrayList.remove(arrayList.get(positionList));
                adapter.notifyDataSetChanged();


                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alarm(getString(R.string.noAdd));

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {

                if (getActivity() != null)
                    Alarm(getString(R.string.noInternet));
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

    public void ShowTrainee() {

        new UserAPI().getTrainee(ApplicationController.getInstance().getLoginUser().getUser_id()+"",
                ApplicationController.getInstance().getLoginUser().getRole_id()+"","1","0",
                new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                TraineeListModel traineeListModel = (TraineeListModel) result;

//                if (responseCategories.isStatus()) {
                loading.setVisibility(View.GONE);

                arrayList.clear();
                arrayList.addAll(traineeListModel.getResult());
                adapter = new AdapterNewTrainee(getActivity(), arrayList, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                    }
                }, new AdapterNewTrainee.MyRecyclerViewListener() {
                    @Override
                    public void Delete(View v, int position) {
                        showDialog(getActivity(),position);
                    }

                    @Override
                    public void Edit(View v, int position) {
                        Intent refresh = new Intent(getContext(), EditTrainee.class);
                        startActivity(refresh);

                    }
                });
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null) {

                        Alarm(getString(R.string.noAdd));
                        loading.setVisibility(View.GONE);
                    }


                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.GONE);


            }

            @Override
            public void OnError(String message) {

                if (getActivity() != null) {
                    Alarm(getString(R.string.noInternet));
                    loading.setVisibility(View.GONE);
                }


            }
        });
    }
    public void showDialog(Activity activity , final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_yes_no);
        FontButtonRegular yes = dialog.findViewById(R.id.yes);
        FontButtonRegular no = dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionList = position;
                DeleteTrainee(arrayList.get(position) + "");
                dialog.hide();

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.hide();
            }
        });
        dialog.show();

    }
}