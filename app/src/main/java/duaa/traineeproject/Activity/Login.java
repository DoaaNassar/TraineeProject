package duaa.traineeproject.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.LoginModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;

public class Login extends AppCompatActivity {
    AlertDialog dialog;


    ArrayList <University> arrayList  =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SpotsDialog(this, R.style.Custom);

        setContentView(R.layout.activity_login);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NavigationMenuActivity.class);
                startActivity(intent);
                login("duaa","123456");

            }
        });

//        Categories();

    }




//    public void Categories() {
//        new UserAPI().getAllUniversity(new UniversalCallBack() {
//            @Override
//            public void onResponse(Object result) {
//
//                UniversityListModel responseCategories = (UniversityListModel) result;
//
////                if (responseCategories.isStatus()) {
//                    arrayList.clear();
//                    arrayList.addAll(responseCategories.getResult());
//                    Log.d("duaabassam",arrayList.get(0).getUniversiy_name()+"hhhh");
////                    categoryAdapter.notifyDataSetChanged();
////                }
//            }
//
//            @Override
//            public void onFailure(Object result) {
//                if (result != null) {
//                    ResponseError responseError = (ResponseError) result;
//                    if (Login.this != null)
//                        Alerter.create(Login.this)
//                                .setText(responseError.getMessage())
//                                .hideIcon()
//                                .setBackgroundColorRes(R.color.colorPrimary)
//                                .show();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void OnError(String message) {
//
//
//                Alerter.create(Login.this)
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();
//
//
//            }
//        });
//
//
//
//}


    public void login(final String Email, final String Password){
        dialog.show();

        new UserAPI().Login(Email, Password, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                LoginModel responseToken = (LoginModel) result;
                Intent intent = new Intent(Login.this, NavigationMenuActivity.class);
                startActivity(intent);
                dialog.hide();
                Log.d("duaaaa",responseToken.getMessage());

            }

            @Override
            public void onFailure(Object result) {
                dialog.hide();

            }

            @Override
            public void onFinish() {
                dialog.hide();

            }

            @Override
            public void OnError(String message) {
                Alerter.create(Login.this)
                        .setText("لا يوجد اتصال بالانترنت ")
                        .hideIcon()
                        .setContentGravity(GravityCompat.END)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .show();

            }
        });
    }

}
