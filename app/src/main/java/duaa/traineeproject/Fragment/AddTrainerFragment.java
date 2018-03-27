package duaa.traineeproject.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.ResponseAddTrainee;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;

public class AddTrainerFragment extends Fragment {

    View view;
    FontEditTextViewRegular name, email, phoneNumber, mobileNumber;
    LinearLayout chooseUniversity, chooseFaculty;
    FontButtonRegular save;
    FrameLayout loadingLayout;
    LinearLayout contentLayout;


    public static AddTrainerFragment newInstance() {
        AddTrainerFragment fragment = new AddTrainerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_trainer, container, false);
        bindView();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    String nameTxt = name.getText().toString();
                    String emailTxt = email.getText().toString();
                    String phoneTxt = phoneNumber.getText().toString();
                    String mobileTxt = mobileNumber.getText().toString();
                    AddItem(new TrainerObject(nameTxt, emailTxt, mobileTxt, "5", "1"
                            , phoneTxt, "15"));
                } else {
                    Toast.makeText(getActivity(), "أضف جميع البيانات", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    public boolean Validate() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(mobileNumber.getText().toString())) {
            return false;
        }
        return true;
    }


    public void AddItem(final TrainerObject item) {
        new UserAPI().AddTrainee(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseAddTrainee responseItem = (ResponseAddTrainee) result;
                String ss = responseItem.getMessage();

                if (responseItem.isStatus()) {

                }


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

    public void bindView() {
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        chooseUniversity = view.findViewById(R.id.university);
        chooseFaculty = view.findViewById(R.id.faculty);
        save = view.findViewById(R.id.save);
        contentLayout = view.findViewById(R.id.contentLayout);
        loadingLayout = view.findViewById(R.id.loadingLayout);


    }


}