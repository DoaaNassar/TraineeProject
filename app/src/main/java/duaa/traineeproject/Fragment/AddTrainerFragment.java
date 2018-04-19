package duaa.traineeproject.Fragment;

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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;

public class AddTrainerFragment extends Fragment {

    View view;
    FontEditTextViewRegular name, email, phoneNumber, mobileNumber;
    LinearLayout chooseUniversity, chooseFaculty;
    FontButtonRegular save;
    FrameLayout loadingLayout;
    LinearLayout contentLayout;
    int height = 0;
    LinearLayout listUniversityLayout, listFacultyLayout;
    ListView universityList, facultyList;
    FontTextViewRegular textUniversity, facultyText;
    ArrayList<University> arrayListUniversity;
    ArrayList<Faculty> arrayListFaculty;
    ImageView imageUniversity, imageFaculty;
    Typeface face;
    boolean isChooseUniversity = false, isChooseFaculty = false ;
    FontTextViewRegular title ;


    public static AddTrainerFragment newInstance() {
        AddTrainerFragment fragment = new AddTrainerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayListUniversity = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_trainer, container, false);
        bindView();

        title.setText("قسم المشرفين");


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
                            .setText("يرجى تعبئة البيانات الناقصة")
                            .hideIcon()
                            .setBackgroundColorRes(R.color.colorPrimary)
                            .setTextTypeface(face)
                            .show();
                }


            }
        });

        chooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UniversityItems();
                arrayListUniversity.clear();
                arrayListUniversity.add(new University(1, "الاسلامية", "gaza", "123"));
                arrayListUniversity.add(new University(1, "الاسلامية", "gaza", "123"));
                arrayListUniversity.add(new University(1, "الاسلامية", "gaza", "123"));

                Log.d("duaabassam", arrayListUniversity.get(0).getUniversiy_name() + "hhhh");
                final AdapterSpinner adapter = new AdapterSpinner(getActivity(), arrayListUniversity);

                adapter.notifyDataSetChanged();
                universityList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(universityList);
                SpinnerAnimation(listUniversityLayout, imageUniversity);
                if (listFacultyLayout.getVisibility() == View.VISIBLE) {
                    SpinnerAnimation(listFacultyLayout, imageFaculty);

                }

            }
        });

        chooseFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FacultyItems();

                if (listUniversityLayout.getVisibility() == View.VISIBLE) {
                    SpinnerAnimation(listUniversityLayout, imageUniversity);

                }
            }
        });

        universityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isChooseUniversity = true;
                textUniversity.setText(arrayListUniversity.get(position).getUniversiy_name());
                SpinnerAnimation(listUniversityLayout, imageUniversity);

            }
        });

        facultyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isChooseFaculty = true;
                SpinnerAnimation(listFacultyLayout, imageFaculty);

            }
        });




        return view;
    }


    public boolean Validate() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(mobileNumber.getText().toString())) {
            return false;
        } else if (!isChooseUniversity) {
            return false;
        } else if (!isChooseUniversity) {
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

        title = getActivity().findViewById(R.id.title);


    }

    public void SpinnerAnimation(LinearLayout view2, ImageView imageView) {
        if (view2.getVisibility() == View.VISIBLE) {
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
            height = a.getHeight();
            view2.startAnimation(a);
            imageView.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        } else {
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.EXPAND);
            a.setHeight(height);
            view2.startAnimation(a);
            imageView.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);

        }
    }


    ////////////*********api***********//////

    ////////GET
    public void UniversityItems() {
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListUniversity.clear();
                arrayListUniversity.addAll(responseCategories.getResult());
                Log.d("duaabassam", arrayListUniversity.get(0).getUniversiy_name() + "hhhh");
                final AdapterSpinner adapter = new AdapterSpinner(getActivity(), arrayListUniversity);


                adapter.notifyDataSetChanged();
                universityList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(universityList);
                SpinnerAnimation(listUniversityLayout, imageUniversity);

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
                        .setText("لا يوجد اتصال بالانترنت")
                        .hideIcon()
                        .setBackgroundColorRes(R.color.colorAccent)
                        .setTextTypeface(face)
                        .show();
            }
        });
    }


    public void FacultyItems() {
        new UserAPI().getAllFaculty("",new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                FacultyListModel responseCategories = (FacultyListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListFaculty.clear();
                arrayListFaculty.addAll(responseCategories.getResult());
                Log.d("duaabassam", arrayListUniversity.get(0).getUniversiy_name() + "hhhh");
                final AdapterSpinner adapter = new AdapterSpinner(getActivity(), arrayListUniversity);


                adapter.notifyDataSetChanged();
                universityList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(universityList);
                SpinnerAnimation(listUniversityLayout, imageUniversity);

                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alerter.create(getActivity())
                                .setText("لا يوجد اتصال بالانترنت")
                                .hideIcon()
                                .setBackgroundColorRes(R.color.colorPrimary)
                                .setTextTypeface(face)
                                .setContentGravity(GravityCompat.END)
                                .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                Alerter.create(getActivity())
                        .setText("لا يوجد اتصال بالانترنت")
                        .hideIcon()
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setTextTypeface(face)
                        .setContentGravity(GravityCompat.END)
                        .show();

            }
        });
//
    }

    ////////////////POST

    public void AddItem(final TrainerObject item) {

        loadingLayout.setVisibility(View.VISIBLE);
        contentLayout.setEnabled(false);
        isBack=true;

        new UserAPI().AddTrainer(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;
                String ss = responseItem.getMessage();

                if (responseItem.isStatus()) {

                    Alerter.create(getActivity())
                            .setText(responseItem.getMessage())
                            .hideIcon()
                            .setBackgroundColorRes(R.color.colorPrimary)
                            .show();
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alerter.create(getActivity())
                            .setText("لم تتم عملية الاضافة")
                            .hideIcon()
                            .setContentGravity(GravityCompat.END)
                            .setBackgroundColorRes(R.color.colorPrimary)
                            .show();
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setEnabled(true);
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                Alerter.create(getActivity())
                        .setText("لا يوجد اتصال بالانترنت ")
                        .hideIcon()
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .show();
                loadingLayout.setVisibility(View.GONE);
                contentLayout.setEnabled(true);

            }
        });
    }


}