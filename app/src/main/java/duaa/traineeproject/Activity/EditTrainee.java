package duaa.traineeproject.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.tapadoo.alerter.Alerter;

import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.UpdateUser;
import duaa.traineeproject.Model.showUserLogin;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;
import es.dmoral.toasty.Toasty;

import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;

public class EditTrainee extends AppCompatActivity {

    FontEditTextViewRegular userName, email, address, phoneNumber, mobileNumber;
    FontEditTextViewRegular oldPassword, newPassword, conform;
    Typeface face;
    Dialog dialogWait ;
    FrameLayout loadingLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trainee);
        bindView();
        setData();
        face = Typeface.createFromAsset(EditTrainee.this.getAssets(), FONTS_APP);


        findViewById(R.id.changePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(EditTrainee.this);
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateProfile())
                 UpdateProfile(new UpdateUser(userName.getText().toString(),email.getText().toString(),phoneNumber.getText().toString(),mobileNumber.getText().toString()));

                else{
                    Alarm("يوجد مشكلة في تعديل البيانات ");
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTrainee.this.finish();
            }
        });
    }

    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.change_password);
        dialog.setCancelable(false);
        oldPassword = dialog.findViewById(R.id.oldPassword);
        newPassword = dialog.findViewById(R.id.newPassword);
        conform = dialog.findViewById(R.id.confirmPassword);
        FontTextViewRegular change = dialog.findViewById(R.id.change);
        FontTextViewRegular end = dialog.findViewById(R.id.endDialog);

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    ChangePassword(oldPassword.getText().toString(),newPassword.getText().toString(),
                            conform.getText().toString());
                } else {
                    Alarm("لم يتم تعبئة جميع البيانات ");
                }


            }
        });


        dialog.show();

    }

    public void bindView() {
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        mobileNumber = findViewById(R.id.mobileNumber);
        phoneNumber = findViewById(R.id.phoneNumber);
        loadingLayout = findViewById(R.id.loadingLayout);

    }

    public void setData() {
        showUserLogin user = ApplicationController.getInstance().getLoginUser();
        userName.setText(user.getUser_name());
        email.setText(user.getEmail());
        mobileNumber.setText(user.getMobile());
        phoneNumber.setText(user.getPhone());
    }

    public boolean Validate() {
        if (TextUtils.isEmpty(oldPassword.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(newPassword.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(conform.getText().toString()))
            return false;

        return true;
    }

    public boolean ValidateProfile() {
        if (TextUtils.isEmpty(userName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(email.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(phoneNumber.getText().toString()))
            return false;

        return true;
    }

    public void ChangePassword(final String oldPassword, String newPassword, String confirmPassword) {

        new UserAPI().UpdatePassword(oldPassword, newPassword, confirmPassword, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;

                if (responseItem.isStatus()) {
                    Toasty.success(EditTrainee.this, ((ResponseSuccess) result).getMessage()).show();

                } else {
                    Toasty.error(EditTrainee.this, ((ResponseSuccess) result).getMessage()).show();

                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getResources().getString(R.string.noAdd));

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (EditTrainee.this != null) {
                    Alarm(getResources().getString(R.string.noInternet));

                }
            }
        });
    }

    public void UpdateProfile(UpdateUser updateUser) {

        showDialogWaiting(EditTrainee.this);
        loadingLayout.setVisibility(View.VISIBLE);

        new UserAPI().UpdateProfile(updateUser , new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;

                if (responseItem.isStatus()) {
                    Toasty.success(EditTrainee.this, ((ResponseSuccess) result).getMessage()).show();

                } else {
                    Toasty.error(EditTrainee.this, ((ResponseSuccess) result).getMessage()).show();

                }
                dialogWait.hide();
                loadingLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getResources().getString(R.string.noAdd));
                    dialogWait.hide();
                    loadingLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {
                dialogWait.hide();
                loadingLayout.setVisibility(View.GONE);
            }

            @Override
            public void OnError(String message) {
                if (EditTrainee.this != null) {
                    Alarm(getResources().getString(R.string.noInternet));
                    dialogWait.hide();

                }
            }
        });
    }

    public void Alarm(String message) {
        Alerter.create(EditTrainee.this)
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }

    public void showDialogWaiting(Activity activity) {
        dialogWait = new Dialog(activity);
        dialogWait.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWait.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogWait.setContentView(R.layout.diii);
        dialogWait.setCancelable(false);

        dialogWait.show();

    }

}
