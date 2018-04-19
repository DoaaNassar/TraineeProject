package duaa.traineeproject.Fragment;

import android.app.NativeActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tapadoo.alerter.Alerter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.TypeSpinnerAdapter;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.JavaObject.Trainee;
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
import duaa.traineeproject.view.MyGlideEngine;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;
import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;

public class AddTrainees extends Fragment {

    View view;
    Uri SelectedImage;
    ImageView imageView, upload;
    RadioGroup gender;

    FontTextViewRegular title;
    private static final int REQUEST_CODE_CHOOSE = 500;
    ArrayList<University> arrayListUniversity;
    ArrayList<String> arrayListType;
    ArrayList<Faculty> arrayListFaculty;
    ArrayList<Specification>arrayListSpecification;
    ListView universityList, typeList, facultyList, specificationList;
    LinearLayout chooseUniversity, chooseFaculty, chooseType;
    LinearLayout listUniversityLayout, listTypeLayout, listFacultyLayout, listSpecificationLayout;
    ImageView imageUniversity, imageType, imageFaculty, imageSpecification;
    FontTextViewRegular titleSpinnerType, titleSpinnerFaculty, titleSpinnerSpecification, titleSpinnerUniversity;
    int height;



    Typeface face;
    FontEditTextViewRegular name, idNumber, mobileNumber, phoneNumber, studentID, address, email, numberHour;
    FontButtonRegular save;
    RadioButton radioButton1, radioButton2;
    String genderText;

    int universityNum, facultyNum, typeNum, specificationNum;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        arrayListUniversity = new ArrayList<>();
        arrayListType = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();
        arrayListSpecification = new ArrayList<>();

        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_trainees, container, false);
        bindView();
        arrayListType.clear();
        arrayListType.add("تدريب جامعي");
        arrayListType.add("تطوع");


        title.setText(getString(R.string.traineePart));
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Matisse.from(AddTrainees.this)
                        .choose(MimeType.of(MimeType.JPEG))
                        .countable(true)
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new MyGlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

            }
        });


        chooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpinnerAnimation(listTypeLayout, imageType);
                final TypeSpinnerAdapter adapter = new TypeSpinnerAdapter(getActivity(), arrayListType);

                adapter.notifyDataSetChanged();
                typeList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(typeList);

                if (listFacultyLayout.getVisibility() == View.VISIBLE) {
                    SpinnerAnimation(listFacultyLayout, imageFaculty);
                }

                if (listUniversityLayout.getVisibility() == View.VISIBLE) {
                    SpinnerAnimation(listUniversityLayout, imageUniversity);
                }

            }
        });


        chooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listUniversityLayout.getVisibility() == View.GONE) {
                    UniversityItem();
                }

                if (listFacultyLayout.getVisibility() == View.VISIBLE) {

                    SpinnerAnimation(listFacultyLayout, imageFaculty);

                }

                if (listTypeLayout.getVisibility() == View.VISIBLE) {

                    SpinnerAnimation(listTypeLayout, imageType);

                }
            }
        });

        chooseFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (universityNum > 0) {
                    if (listFacultyLayout.getVisibility() == View.GONE) {
                        FacultyItems(arrayListUniversity.get(universityNum-1).getUniversiy_id()+"");
                    }

                    if (listTypeLayout.getVisibility() == View.VISIBLE) {
                        SpinnerAnimation(listTypeLayout, imageType);
                    }

                    if (listUniversityLayout.getVisibility() == View.VISIBLE) {
                        SpinnerAnimation(listUniversityLayout, imageUniversity);
                    }
                } else {
                    Alarm("أضف الجامعة أولا ");
                }
            }

        });


        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeNum = position + 1;
                titleSpinnerType.setText(arrayListType.get(position));
                SpinnerAnimation(listTypeLayout, imageType);

            }
        });

        universityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                universityNum = position + 1;
                titleSpinnerUniversity.setText(arrayListUniversity.get(position).getUniversiy_name());
                SpinnerAnimation(listUniversityLayout, imageUniversity);

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validate()){


//                    AddItem(new Trainee(name.getText().toString(),,mobileNumber.getText().toString()
//                            ,phoneNumber.getText().toString() ,email.getText().toString(),
//                            arrayListUniversity.get(universityNum-1).getUniversiy_id()+"",arrayListFaculty.get(facultyNum-1).getFaculty_id()+"",
//                            arrayListSpecification.get(specificationNum-1).getId()+"",
//                            arrayListType.get(typeNum-1),studentID.getText().toString(),numberHour.getText().toString()
//                    )));

                }else{
                    Alarm("لم تتم إضافة جميع البيانات اللازمة");

                }


            }
        });

        return view;
    }


    public void bindView() {

        upload = view.findViewById(R.id.upload);
        imageView = view.findViewById(R.id.image);
        name = view.findViewById(R.id.traineeName);
        email = view.findViewById(R.id.email);
        idNumber = view.findViewById(R.id.idNumber);
        studentID = view.findViewById(R.id.studentID);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        address = view.findViewById(R.id.address);
        gender = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radio1);
        radioButton2 = view.findViewById(R.id.radio2);
        numberHour = view.findViewById(R.id.hourNumber);

        chooseUniversity = view.findViewById(R.id.university);
        chooseFaculty = view.findViewById(R.id.faculty);
        chooseType = view.findViewById(R.id.type);


        universityList = view.findViewById(R.id.universitySpinner);
        typeList = view.findViewById(R.id.typeSpinner);
        facultyList = view.findViewById(R.id.facultySpinner);
        specificationList = view.findViewById(R.id.specificationSpinner);

        listUniversityLayout = view.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = view.findViewById(R.id.facultySpinnerLayout);
        listTypeLayout = view.findViewById(R.id.typeSpinnerLayout);
        listSpecificationLayout = view.findViewById(R.id.specificationSpinnerLayout);

        imageFaculty = view.findViewById(R.id.facultyImage);
        imageType = view.findViewById(R.id.iconType);
        imageSpecification = view.findViewById(R.id.specImage);
        imageUniversity = view.findViewById(R.id.imageUniversity);

        titleSpinnerFaculty = view.findViewById(R.id.facultyText);
        titleSpinnerSpecification = view.findViewById(R.id.textSpec);
        titleSpinnerUniversity = view.findViewById(R.id.textUniversity);
        titleSpinnerType = view.findViewById(R.id.textType);

        save = view.findViewById(R.id.save);

        title = getActivity().findViewById(R.id.title);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ffffff", requestCode + "123456");

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            SelectedImage = Matisse.obtainResult(data).get(0);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.add_user);
            requestOptions.error(R.drawable.delete_user);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Log.d("selectAll", SelectedImage + "");

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(SelectedImage).thumbnail(.1f).into(imageView);
            imageView.setVisibility(View.VISIBLE);


        }


    }


    public boolean Validate() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(mobileNumber.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(idNumber.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(numberHour.getText().toString())) {
            return false;
        } else if (!radioButton1.isChecked() && !radioButton2.isChecked()) {
            return false;
        } else if (typeNum < 1) {
            return false;
        } else if (typeNum == 1) {
            if (specificationNum > 1) {
            } else if (universityNum < 1) {
                return false;
            } else if (facultyNum < 1) {
                return false;
            }
        }
        return true;
    }

    public void UniversityItem() {
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
                SpinnerAnimation(listUniversityLayout, imageUniversity);

                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alarm(getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                Alarm(getString(R.string.noInternet));
            }
        });
//
    }


    public void FacultyItems(String university_id) {
        new UserAPI().getAllFaculty(university_id,new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                FacultyListModel responseCategories = (FacultyListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListFaculty.clear();
                arrayListFaculty.addAll(responseCategories.getResult());
                final AdapterSpinnerFaculty adapter = new AdapterSpinnerFaculty(getActivity(), arrayListFaculty);


                adapter.notifyDataSetChanged();
                facultyList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(facultyList);
                SpinnerAnimation(listFacultyLayout, imageFaculty);

                //                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    if (getActivity() != null)
                        Alarm(getString(R.string.noAdd));

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {

                Alarm(getString(R.string.noInternet));
            }
        });
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


    public void AddItem(final Trainee item) {

        new UserAPI().AddTrainee(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;

                if (responseItem.isStatus()) {
                    Alarm(responseItem.getMessage());

                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                   Alarm(getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
              Alarm(getString(R.string.noInternet));

            }
        });
    }

    public void Alarm(String message) {
        Alerter.create(getActivity())
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }


}