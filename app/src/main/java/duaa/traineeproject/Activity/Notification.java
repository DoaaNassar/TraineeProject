package duaa.traineeproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import duaa.traineeproject.Adapter.AdapterNewTrainee;
import duaa.traineeproject.Adapter.AdapterNotification;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.NotificationModel;
import duaa.traineeproject.R;

public class Notification extends AppCompatActivity {

    ArrayList<NotificationModel>arrayList,arrayListCheck ;
    AdapterNotification adapter ;
    RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        arrayListCheck = new ArrayList<>();
        arrayList.clear();
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));
        arrayList.add(new NotificationModel("دعاء","دعاء"));


        adapter = new AdapterNotification(Notification.this, arrayList,arrayListCheck,
                new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new AdapterNotification.MyRecyclerViewListener() {

            @Override
            public void check(NotificationModel v, int position) {
                arrayListCheck.add(v) ;
            }
        } );
        LinearLayoutManager llm = new LinearLayoutManager(Notification.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
