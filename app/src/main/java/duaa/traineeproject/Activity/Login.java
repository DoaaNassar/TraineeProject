package duaa.traineeproject.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.LoginModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;

public class Login extends AppCompatActivity {
    AlertDialog dialog;

    Typeface face;
    FontEditTextViewRegular email, password;
    FontButtonRegular loginBtn;

    ArrayList<University> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        face = Typeface.createFromAsset(this.getAssets(), FONTS_APP);
        setContentView(R.layout.activity_login);

        bindView();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validate())
                    login("eng.randlulu@gmail.com", "147");
                else
                    Alarm("أرجو ادخال الايميل و كلمة المرور");

            }
        });

    }

    public void login(final String Email, final String Password) {
        dialog.show();

        new UserAPI().Login(Email, Password, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                LoginModel loginModel = (LoginModel) result;
                Login.this.finish();
                Intent intent = new Intent(Login.this, NavigationMenuActivity.class);
                startActivity(intent);

                ApplicationController.getInstance().userLogin(loginModel.getResult());
                ApplicationController.getInstance().login_token(loginModel.getResult().getAccess_token());
                Alarm(loginModel.getMessage());
                dialog.hide();

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
                dialog.hide();
                if (Login.this != null)
                    Alarm(getString(R.string.noInternet));

            }
        });
    }


    public void Alarm(String message) {
        Alerter.create(Login.this)
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }

    public void bindView() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        dialog = new SpotsDialog(this, R.style.Custom);

    }


    public boolean Validate() {
        if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            return false;
        }
        return true;
    }
}
