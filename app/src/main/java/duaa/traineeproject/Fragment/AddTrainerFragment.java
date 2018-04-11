package duaa.traineeproject.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.ListTraineeAdapter;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.ResponseAddTrainee;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;

public class AddTrainerFragment extends Fragment {

    View view;
    FontEditTextViewRegular name, email, phoneNumber, mobileNumber;
    LinearLayout chooseUniversity, chooseFaculty;
    FontButtonRegular save;
    FrameLayout loadingLayout;
    LinearLayout contentLayout;
    int height = 0;
    LinearLayout listUniversityLayout ,listFacultyLayout;
    ListView universityList ,facultyList;
    FontTextViewRegular textUniversity ,facultyText;
    ArrayList <University>arrayListUniversity ;
    ImageView imageUniversity ,imageFaculty ;
    Typeface face;






    public static AddTrainerFragment newInstance() {
        AddTrainerFragment fragment = new AddTrainerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayListUniversity = new ArrayList<>();
        face= Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_trainer, container, false);
        bindView();

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
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

                    Alerter.create(getActivity())
                            .setText("يرجى تكملة البيانات الناقصة")
                            .hideIcon()
                            .setBackgroundColorRes(R.color.colorPrimary)
                            .setTextTypeface(face)
                            .show();
                }



//                Animation a = new ScaleAnimation(1, 1, 0, 1, Animation.RELATIVE_TO_SELF,
//                        (float) 0.5,    Animation.RELATIVE_TO_SELF, (float) 0);
//                a.setFillAfter(true);
//                view.setAnimation(a);
//                a.setDuration(1000);
//                view.startAnimation(a);

            }
        });

        chooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories();

            }
        });



        universityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SpinnerAnimation(listUniversityLayout,imageUniversity);

            }
        });


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




    public void bindView() {
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        chooseUniversity = view.findViewById(R.id.chooseUniversity);
        chooseFaculty = view.findViewById(R.id.chooseFaculty);
        save = view.findViewById(R.id.save);
        contentLayout = view.findViewById(R.id.contentLayout);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);
        listUniversityLayout = view.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = view.findViewById(R.id.facultySpinnerLayout);
        universityList = view.findViewById(R.id.universitySpinner);
        facultyList = view.findViewById(R.id.facultySpinner);
        textUniversity = view.findViewById(R.id.textUniversity);
        facultyText = view.findViewById(R.id.facultyText);
        imageUniversity = view.findViewById(R.id.imageUniversity);
        imageFaculty = view.findViewById(R.id.faculty);



    }

    public void SpinnerAnimation(LinearLayout view2 , ImageView imageView){
        if(view2.getVisibility() == View.VISIBLE){
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
            height = a.getHeight();
            view2.startAnimation(a);
            imageView.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }else{
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.EXPAND);
            a.setHeight(height);
            view2.startAnimation(a);
            imageView.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);

        }
    }

    public void Categories() {
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListUniversity.clear();
                arrayListUniversity.addAll(responseCategories.getResult());
                Log.d("duaabassam", arrayListUniversity.get(0).getUniversiy_name() + "hhhh");
                final AdapterSpinner adapter = new AdapterSpinner(getActivity(), arrayListUniversity) ;


                adapter.notifyDataSetChanged();
                universityList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(universityList);
                SpinnerAnimation(listUniversityLayout,imageUniversity);


                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alerter.create(getActivity())
                                .setText(responseError.getMessage())
                                .hideIcon()
                                .setBackgroundColorRes(R.color.colorPrimary)
                                .setTextTypeface(face)
                                .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {


                Alerter.create(getActivity())
                        .setText(message)
                        .hideIcon()
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setTextTypeface(face)
                        .show();


            }
        });
//
    }


    public void AddItem(final TrainerObject item) {

        loadingLayout.setVisibility(View.VISIBLE);
        contentLayout.setEnabled(false);

        new UserAPI().AddTrainee(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseAddTrainee responseItem = (ResponseAddTrainee) result;
                String ss = responseItem.getMessage();

                if (responseItem.isStatus()) {
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setEnabled(true);
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

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }

}