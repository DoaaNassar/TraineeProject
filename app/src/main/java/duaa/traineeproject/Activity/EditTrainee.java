package duaa.traineeproject.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

public class EditTrainee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trainee);

        findViewById(R.id.changePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(EditTrainee.this);
            }
        });
    }

    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.change_password);
        dialog.setCancelable(false);
        FontEditTextViewRegular oldPassword = dialog.findViewById(R.id.oldPassword);
        FontEditTextViewRegular newPassword = dialog.findViewById(R.id.newPassword);
        FontEditTextViewRegular conform = dialog.findViewById(R.id.confirmPassword);
        FontTextViewRegular change = dialog.findViewById(R.id.change);
        FontTextViewRegular end = dialog.findViewById(R.id.endDialog);

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

            }
        });


        dialog.show();

    }


}
