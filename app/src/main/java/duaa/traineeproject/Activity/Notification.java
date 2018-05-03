package duaa.traineeproject.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterNewTrainee;
import duaa.traineeproject.Adapter.AdapterNotification;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.AddFacultyModel;
import duaa.traineeproject.Model.NotificationModel;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.Model.TraineeListModel;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;

public class Notification extends AppCompatActivity {

    ArrayList<TraineeModel> arrayList ,checkArray ;
    List<Integer>checkList ;
    AdapterNotification adapter;
    RecyclerView recyclerView;
    FontTextViewRegular approve , disapprove;
    FrameLayout loadingLayout;
    Dialog dialog ;
    Typeface face;
    LinearLayout loading ;
    FontTextViewRegular noData ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerview);
        approve = findViewById(R.id.approve);
        disapprove = findViewById(R.id.disapprove);
        loadingLayout = findViewById(R.id.loadingLayout);
        loading = findViewById(R.id.loading);
        noData = findViewById(R.id.noData);
        face = Typeface.createFromAsset(Notification.this.getAssets(), FONTS_APP);
        checkList = new ArrayList<>();

        arrayList = new ArrayList<>();
        checkArray = new ArrayList<>();

        arrayList.clear();
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));
        arrayList.add(new TraineeModel(1,1, "دعاء","دعاء" ,"دعاء",
                "دعاء","123456",1,1,1,1,"دعاء"));



        adapter = new AdapterNotification(Notification.this, arrayList,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                    }
                }, new AdapterNotification.MyRecyclerViewListener() {


            @Override
            public void check(NotificationModel v, boolean check, int position) {
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(Notification.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        ShowNotification();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkArray.addAll(adapter.getData().values());
                if (checkArray.size()!=0){
                for (int i = 0 ; i <checkArray.size() ; i++){
                    checkList.add(checkArray.get(i).getTrainee_id());
                }

                ApproveNotification(checkList,1);
                Log.d("adaptersize", "" + adapter.getData().size());
            }else {
                Alarm("لا تحدد عناصر :)");
            }}
        });

        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkArray.addAll(adapter.getData().values());
                if (checkArray.size()!=0){
                for (int i = 0 ; i <checkArray.size() ; i++){
                    checkList.add(checkArray.get(i).getTrainee_id());
                }
                ApproveNotification(checkList,0);
                Log.d("adaptersize", "" + adapter.getData().size());
            }else {
                Alarm("لم تحدد عناصر :)");
                }
            }
        });


    }
    public void ApproveNotification(final List<Integer> item , int approve) {
        loadingLayout.setVisibility(View.VISIBLE);
        showDialog(Notification.this);

        new UserAPI().Notification(item,approve, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseTrue responseItem = (ResponseTrue) result;
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();
//                if (responseItem.isStatus()) {
                Alarm(responseItem.getMessage());

//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getResources().getString(R.string.noAdd));
                    loadingLayout.setVisibility(View.GONE);
                    dialog.hide();

                }
            }

            @Override
            public void onFinish() {
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();

            }

            @Override
            public void OnError(String message) {
                Alarm("لا يوجد انترنت");
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();

            }
        });
    }


    public void ShowNotification() {

        new UserAPI().getTrainee(ApplicationController.getInstance().getLoginUser().getUser_id()+"",
                ApplicationController.getInstance().getLoginUser().getRole_id()+"","0","",
                new UniversalCallBack() {
                    @Override
                    public void onResponse(Object result) {

                        TraineeListModel traineeListModel = (TraineeListModel) result;

//                if (responseCategories.isStatus()) {
                        loading.setVisibility(View.GONE);

                        arrayList.clear();
                        arrayList.addAll(traineeListModel.getResult());
                        if (arrayList.size()!=0) {
                            adapter = new AdapterNotification(Notification.this, arrayList, new CustomItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {

                                }
                            }, new AdapterNotification.MyRecyclerViewListener() {

                                @Override
                                public void check(NotificationModel v, boolean check, int position) {

                                }
                            });
                            LinearLayoutManager llm = new LinearLayoutManager(Notification.this);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            noData.setVisibility(View.VISIBLE);

                        }

                        //                }
                    }

                    @Override
                    public void onFailure(Object result) {
                        if (result != null) {
                            ResponseError responseError = (ResponseError) result;
                            if (Notification.this != null) {

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

                        if (Notification.this != null) {
                            Alarm(getString(R.string.noInternet));
                            loading.setVisibility(View.GONE);
                        }


                    }
                });
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.diii);
        dialog.setCancelable(false);

        dialog.show();

    }

    public void Alarm(String message) {
        Alerter.create(Notification.this)
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }


}
