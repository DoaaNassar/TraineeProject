package duaa.traineeproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TraineeObject;
import duaa.traineeproject.Model.LoginModel;
import duaa.traineeproject.Model.ResponseAddTrainee;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.ResponseError;
import duaa.traineeproject.UserAPI;

public class Login extends AppCompatActivity {


    ArrayList <University> arrayList  =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, navigationMenuActivity.class);
                startActivity(intent);
            }
        });

        Categories();
        login("duaa","duaa");

        AddItem(new TraineeObject("duaa","dbn.95@hotmail.com","0599465605"
                ,"computer","islamic","059555","duaa"));

    }

    public void AddItem( final TraineeObject item){
        new UserAPI().AddTrainee(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseAddTrainee responseItem = (ResponseAddTrainee) result;
                String ss = responseItem.getMessage();
                Log.d("duaaaa",ss);

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {

                }

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
//                Alerter.create(AddItemActivity.this)
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();
//                if(pDialog.isShowing()){
//                    pDialog.dismissWithAnimation();
//                }
            }
        });
    }



    public void Categories() {
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;

//                if (responseCategories.isStatus()) {
                    arrayList.clear();
                    arrayList.addAll(responseCategories.getResult());
                    Log.d("duaabassam",arrayList.get(0).getUniversiy_name()+"hhhh");
//                    categoryAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    if (getActivity() != null)
//                        Alerter.create(getActivity())
//                                .setText(responseError.getMessage())
//                                .hideIcon()
//                                .setBackgroundColorRes(R.color.colorPrimary)
//                                .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {

//                Alerter.create(getActivity())
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();


            }
        });



}


    public void login(final String Email, final String Password){

        new UserAPI().Login(Email, Password, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                LoginModel responseToken = (LoginModel) result;
                Log.d("duaaaa",responseToken.getMessage());

            }

            @Override
            public void onFailure(Object result) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {

            }
        });
    }

}
