package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.NativeActivity;
import android.content.Context;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tapadoo.alerter.Alerter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Activity.MainActivity;
import duaa.traineeproject.Activity.NavigationMenuActivity;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.AdapterSpinnerPartPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerspecialization;
import duaa.traineeproject.Adapter.TypeSpinnerAdapter;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.PartObject;
import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.JavaObject.Trainee;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.PartPlaceListModel;
import duaa.traineeproject.Model.PartPlaceModel;
import duaa.traineeproject.Model.PlaceListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.SpecializationListModel;
import duaa.traineeproject.Model.SpecializationModel;
import duaa.traineeproject.Model.TraineeAddModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.Units.Utility;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;
import duaa.traineeproject.view.MyGlideEngine;
import es.dmoral.toasty.Toasty;
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
    ArrayList<SpecializationModel> arrayListspecialization;
    ArrayList<PlaceModel> arrayListPlace;
    ArrayList<PartPlaceModel> arrayListPartPlace;

    ListView universityList, typeList, facultyList, specializationList, placeList, partPlaceList;
    LinearLayout chooseUniversity, chooseFaculty, chooseType, choosespecialization, choosePlace, choosePartPlace, studentLayout;
    LinearLayout listUniversityLayout, listTypeLayout, listFacultyLayout, listspecializationLayout, listPlaceLayout, listPartPlaceLayout;
    ImageView imageUniversity, imageType, imageFaculty, imagespecialization, imagePlace, imagePartPlace;
    FontTextViewRegular titleSpinnerType, titleSpinnerFaculty, titleSpinnerspecialization, titleSpinnerUniversity, titleSpinnerPlace, titleSpinnerPartPlace;

    int height;
    Typeface face;
    FontEditTextViewRegular name, idNumber, mobileNumber, phoneNumber, studentID, address, email, numberHour;
    FontButtonRegular save;
    RadioButton radioButton1, radioButton2;
    String genderText;

    int universityNum, facultyNum, typeNum, specializationNum, placeNum, partPlaceNum;
    String idTrainee;
    ImageView search;
    FrameLayout loadingLayout;
    Dialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        arrayListUniversity = new ArrayList<>();
        arrayListType = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();
        arrayListspecialization = new ArrayList<>();
        arrayListPlace = new ArrayList<>();
        arrayListPartPlace = new ArrayList<>();

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
        search.setVisibility(View.GONE);

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

                final TypeSpinnerAdapter adapter = new TypeSpinnerAdapter(getActivity(), arrayListType);

                adapter.notifyDataSetChanged();
                typeList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(typeList);

                SpinnerAnimation(listTypeLayout, imageType);
                SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                SpinnerAnimationClose(listPartPlaceLayout, imagePartPlace);
                SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                SpinnerAnimationClose(listPlaceLayout, imagePlace);

            }
        });


        chooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listUniversityLayout.getVisibility() == View.GONE) {
                    UniversityItem();
                }
                SpinnerAnimationClose(listTypeLayout, imageType);
                SpinnerAnimation(listUniversityLayout, imageUniversity);
                SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                SpinnerAnimationClose(listPartPlaceLayout, imagePartPlace);
                SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                SpinnerAnimationClose(listPlaceLayout, imagePlace);
            }
        });

        chooseFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (universityNum > 0) {
                    if (listFacultyLayout.getVisibility() == View.GONE) {
                        Log.d("duaaa", arrayListUniversity.get(universityNum - 1).getUniversiy_id() + "");
                        FacultyItems(arrayListUniversity.get(universityNum - 1).getUniversiy_id() + "");
                    }
                    SpinnerAnimation(listFacultyLayout, imageFaculty);
                    SpinnerAnimationClose(listTypeLayout, imageType);
                    SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                    SpinnerAnimationClose(listPartPlaceLayout, imagePartPlace);
                    SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                    SpinnerAnimationClose(listPlaceLayout, imagePlace);

                } else {
                    Alarm("أضف الجامعة أولا ");
                }
            }

        });

        choosePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listPlaceLayout.getVisibility() == View.GONE) {
                    PlaceItems();
                }

                SpinnerAnimationClose(listTypeLayout, imageType);
                SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                SpinnerAnimationClose(listPartPlaceLayout, imagePartPlace);
                SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                SpinnerAnimation(listPlaceLayout, imagePlace);


            }

        });

        choosePartPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (placeNum > 0) {
                    if (listPartPlaceLayout.getVisibility() == View.GONE) {
                        Log.d("ffffff", arrayListPlace.get(placeNum - 1).getPlace_id() + "");
                        PartPlaceItem(arrayListPlace.get(placeNum - 1).getPlace_id() + "");
                    }
                    SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                    SpinnerAnimationClose(listTypeLayout, imageType);
                    SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                    SpinnerAnimation(listPartPlaceLayout, imagePartPlace);
                    SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                    SpinnerAnimationClose(listPlaceLayout, imagePlace);

                } else {
                    Alarm("أضف القسم الرئيسي أولا :)");
                }
            }

        });

        choosespecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (facultyNum > 0) {
                    if (listspecializationLayout.getVisibility() == View.GONE) {
                        SpecializationItems(arrayListFaculty.get(facultyNum - 1).getCollage_id() + "");
                    }
                    SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                    SpinnerAnimationClose(listTypeLayout, imageType);
                    SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                    SpinnerAnimationClose(listPartPlaceLayout, imagePartPlace);
                    SpinnerAnimation(listspecializationLayout, imagespecialization);
                    SpinnerAnimationClose(listPlaceLayout, imagePlace);

                } else {
                    Alarm("أضف الكلية أولا ");
                }
            }
        });

        choosePartPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeNum > 0) {
                    if (listPartPlaceLayout.getVisibility() == View.GONE) {
                        PartPlaceItem(arrayListPlace.get(placeNum - 1).getPlace_id() + "");
                    }
                    SpinnerAnimationClose(listFacultyLayout, imageFaculty);
                    SpinnerAnimationClose(listTypeLayout, imageType);
                    SpinnerAnimationClose(listUniversityLayout, imageUniversity);
                    SpinnerAnimation(listPartPlaceLayout, imagePartPlace);
                    SpinnerAnimationClose(listspecializationLayout, imagespecialization);
                    SpinnerAnimationClose(listPlaceLayout, imagePlace);

                } else {
                    Alarm("أضف القسم أولا ");
                }
            }
        });

        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerAnimation(studentLayout, position);
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

        placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                placeNum = position + 1;
                titleSpinnerPlace.setText(arrayListPlace.get(position).getPlace_name());
                SpinnerAnimation(listPlaceLayout, imagePlace);

            }
        });

        facultyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                facultyNum = position + 1;
                titleSpinnerFaculty.setText(arrayListFaculty.get(position).getCollage_name());
                SpinnerAnimation(listFacultyLayout, imageFaculty);

            }
        });

        specializationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                specializationNum = position + 1;
                titleSpinnerspecialization.setText(arrayListspecialization.get(position).getSpecalization_name());
                SpinnerAnimation(listspecializationLayout, imagespecialization);
            }
        });

        partPlaceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                partPlaceNum = position + 1;
                titleSpinnerPartPlace.setText(arrayListPartPlace.get(position).getPartment_name());
                SpinnerAnimation(listPartPlaceLayout, imagePartPlace);
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                if (index == 0)
                    genderText = "ذكر";
                else
                    genderText = "أنثى";
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {

                    if (arrayListType.get(typeNum - 1).equals("تدريب جامعي")) {

                        AddItem(new Trainee(name.getText().toString(), genderText, mobileNumber.getText().toString(),
                                phoneNumber.getText().toString(), email.getText().toString(), arrayListUniversity.get(universityNum - 1).getUniversiy_id() + "",
                                arrayListFaculty.get(facultyNum - 1).getCollage_id() + "", arrayListspecialization.get(specializationNum - 1).getSpecalization_id() + ""
                                , arrayListType.get(typeNum - 1), studentID.getText().toString(), numberHour.getText().toString(),
                                arrayListPlace.get(placeNum - 1).getPlace_id() + "",
                                arrayListPartPlace.get(partPlaceNum - 1).getPlace_partment_id() + "",
                                idNumber.getText().toString(), ApplicationController.getInstance().getLoginUser().getRole_id() + "",
                                ApplicationController.getInstance().getLoginUser().getUser_id() + ""));

                    } else {
                        AddItem(new Trainee(name.getText().toString(), genderText, mobileNumber.getText().toString(),
                                phoneNumber.getText().toString(), email.getText().toString()
                                , arrayListType.get(typeNum - 1), numberHour.getText().toString(),
                                arrayListPlace.get(placeNum - 1).getPlace_id() + "",
                                arrayListPartPlace.get(partPlaceNum - 1).getPlace_partment_id() + "",
                                idNumber.getText().toString(), ApplicationController.getInstance().getLoginUser().getRole_id() + "",
                                ApplicationController.getInstance().getLoginUser().getUser_id() + ""));
                    }
                } else Alarm("لم تتم إضافة جميع البيانات اللازمة");


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
        choosePlace = view.findViewById(R.id.place);
        choosespecialization = view.findViewById(R.id.specialization);
        choosePartPlace = view.findViewById(R.id.partPlace);


        universityList = view.findViewById(R.id.universitySpinner);
        typeList = view.findViewById(R.id.typeSpinner);
        facultyList = view.findViewById(R.id.facultySpinner);
        specializationList = view.findViewById(R.id.specializationSpinner);
        placeList = view.findViewById(R.id.placeSpinner);
        partPlaceList = view.findViewById(R.id.partPlaceSpinner);

        listUniversityLayout = view.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = view.findViewById(R.id.facultySpinnerLayout);
        listTypeLayout = view.findViewById(R.id.typeSpinnerLayout);
        listspecializationLayout = view.findViewById(R.id.specializationSpinnerLayout);
        listPlaceLayout = view.findViewById(R.id.placeSpinnerLayout);
        listPartPlaceLayout = view.findViewById(R.id.partPlaceSpinnerLayout);

        imageFaculty = view.findViewById(R.id.facultyImage);
        imageType = view.findViewById(R.id.iconType);
        imagespecialization = view.findViewById(R.id.specImage);
        imageUniversity = view.findViewById(R.id.imageUniversity);
        imagePlace = view.findViewById(R.id.imagePlace);
        imagePartPlace = view.findViewById(R.id.imagePartPlace);

        titleSpinnerFaculty = view.findViewById(R.id.facultyText);
        titleSpinnerUniversity = view.findViewById(R.id.textUniversity);
        titleSpinnerspecialization = view.findViewById(R.id.textSpec);
        titleSpinnerType = view.findViewById(R.id.textType);
        titleSpinnerPlace = view.findViewById(R.id.textPlace);
        titleSpinnerPartPlace = view.findViewById(R.id.textPartPlace);

        studentLayout = view.findViewById(R.id.studentLayout);

        save = view.findViewById(R.id.save);

        title = getActivity().findViewById(R.id.title);
        search = getActivity().findViewById(R.id.search);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ffffff", requestCode + "123456");

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            SelectedImage = Matisse.obtainResult(data).get(0);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.user_new);
            requestOptions.error(R.drawable.user_new);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Log.d("selectAll", SelectedImage + "");

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(SelectedImage).thumbnail(.1f).into(imageView);
            imageView.setVisibility(View.VISIBLE);


        }


    }


    private void UploadImage(Uri SelectedImage) {
        InputStream iStream = null;
        try {
            Context applicationContext = getActivity().getApplicationContext();
            iStream = applicationContext.getContentResolver().openInputStream(SelectedImage);
            byte[] image = Utility.getBytes(iStream);
            Log.d("duaaa", idTrainee + "");
            UploadUserImage(ApplicationController.getInstance().token(),
                    image, idTrainee);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            if (specializationNum > 1) {
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
                UIUtils.setListViewHeightBasedOnItems(universityList);


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
        new UserAPI().getAllFaculty(university_id, new UniversalCallBack() {
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


    public void SpecializationItems(String collage_id) {
        new UserAPI().getAllSpecialization(collage_id, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                SpecializationListModel responseSpecialization = (SpecializationListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListspecialization.clear();
                arrayListspecialization.addAll(responseSpecialization.getResult());
                final AdapterSpinnerspecialization adapter = new AdapterSpinnerspecialization(getActivity(), arrayListspecialization);


                adapter.notifyDataSetChanged();
                specializationList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(specializationList);

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

    public void PartPlaceItem(String place_id) {
        new UserAPI().getPartPlace(place_id, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                PartPlaceListModel responsePartPlace = (PartPlaceListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListPartPlace.clear();
                arrayListPartPlace.addAll(responsePartPlace.getResult());
                final AdapterSpinnerPartPlace adapter = new AdapterSpinnerPartPlace(getActivity(), arrayListPartPlace);

                adapter.notifyDataSetChanged();
                partPlaceList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(partPlaceList);

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

    public void PlaceItems() {
        new UserAPI().getPlace(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                PlaceListModel responsePlace = (PlaceListModel) result;

//                if (responseCategories.isStatus()) {
                arrayListPlace.clear();
                arrayListPlace.addAll(responsePlace.getResult());
                final AdapterSpinnerPlace adapter = new AdapterSpinnerPlace(getActivity(), arrayListPlace);


                adapter.notifyDataSetChanged();
                placeList.setAdapter(adapter);
                UIUtils.setListViewHeightBasedOnItems(placeList);

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


    public void AddItem(final Trainee item) {

        loadingLayout.setVisibility(View.VISIBLE);
        showDialog(getActivity());

        new UserAPI().AddTrainee(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                TraineeAddModel responseItem = (TraineeAddModel) result;

                if (responseItem.isStatus()) {
                    Alarm(responseItem.getMessage());
                    Toasty.success(getActivity(), responseItem.getMessage(), Toast.LENGTH_SHORT, true).show();
                    idTrainee = responseItem.getTrainee_data_id();
                    if (!TextUtils.isEmpty(SelectedImage + "")) {
                        UploadImage(SelectedImage);
                    } else {
                        dialog.hide();
                        loadingLayout.setVisibility(View.GONE);
                    }
                }
                Alarm(responseItem.getMessage());


            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getString(R.string.noAdd));
                    dialog.hide();
                    loadingLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null)
                    Alarm(getString(R.string.noInternet));
                dialog.hide();
                loadingLayout.setVisibility(View.GONE);

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

    public void SpinnerAnimation(LinearLayout view2, int number) {
        if (number == 1) {
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
            height = a.getHeight();
            view2.startAnimation(a);
        } else {
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.EXPAND);
            a.setHeight(height);
            view2.startAnimation(a);
        }
    }

    public void SpinnerAnimationClose(LinearLayout view2, ImageView imageView) {
        if (view2.getVisibility() == View.VISIBLE) {
            MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
            height = a.getHeight();
            view2.startAnimation(a);
            imageView.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }

    }

    public void UploadUserImage(final String token, final byte[] photo, String traineeData_id) {
        new UserAPI().UploadUserImage(token, photo, traineeData_id, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                dialog.hide();
                loadingLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    dialog.hide();
                    loadingLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {
                dialog.hide();
                loadingLayout.setVisibility(View.GONE);

            }

            @Override
            public void OnError(String message) {
                dialog.hide();
                loadingLayout.setVisibility(View.GONE);
            }
        });
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.diii);
        dialog.setCancelable(false);

        dialog.show();

    }
}