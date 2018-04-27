package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tapadoo.alerter.Alerter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Activity.EditTrainee;
import duaa.traineeproject.Activity.NavigationMenuActivity;
import duaa.traineeproject.Activity.TrainingActivity;
import duaa.traineeproject.Adapter.AdapterNewTrainee;
import duaa.traineeproject.Adapter.AdapterOldTrainee;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.AdapterSpinnerPartPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerPlace;
import duaa.traineeproject.Adapter.AdapterSpinnerspecialization;
import duaa.traineeproject.Adapter.ListTraineeAdapter;
import duaa.traineeproject.Adapter.OldTraineeAdapter;
import duaa.traineeproject.Adapter.OldTrainerAdapter;
import duaa.traineeproject.Adapter.TypeSpinnerAdapter;
import duaa.traineeproject.Application.ApplicationController;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.AntherTraining;
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
import duaa.traineeproject.Model.TraineeListModel;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;
import duaa.traineeproject.view.MyGlideEngine;

import static android.app.Activity.RESULT_OK;
import static duaa.traineeproject.Constants.FONTS_APP;


public class OldTraineeFragment extends Fragment {

    View view;
    FontTextViewRegular title;
    AdapterOldTrainee adapter;
    RecyclerView recyclerView;
    LinearLayout loading;
    ArrayList<TraineeModel> arrayList;
    Typeface face;
    int height;
    int universityNum, facultyNum, typeNum, specializationNum, placeNum;
    ListView universityList, typeList, facultyList, specializationList, placeList, partPlaceList;
    LinearLayout chooseUniversity, chooseFaculty, chooseType, choosespecialization, choosePlace, choosePartPlace, studentLayout;
    LinearLayout listUniversityLayout, listTypeLayout, listFacultyLayout, listspecializationLayout, listPlaceLayout, listPartPlaceLayout;
    ImageView imageUniversity, imageType, imageFaculty, imagespecialization, imagePlace, imagePartPlace;
    FontTextViewRegular titleSpinnerType, titleSpinnerFaculty, titleSpinnerspecialization, titleSpinnerUniversity, titleSpinnerPlace, titleSpinnerPartPlace;
    FontEditTextViewRegular numberHour;

    ArrayList<University> arrayListUniversity;
    ArrayList<String> arrayListType;
    ArrayList<Faculty> arrayListFaculty;
    ArrayList<SpecializationModel> arrayListspecialization;
    ArrayList<PlaceModel> arrayListPlace;
    ArrayList<PartPlaceModel> arrayListPartPlace;

    ImageView upload , imageView;
    Uri SelectedImage;

    private static final int REQUEST_CODE_CHOOSE = 500;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);
        arrayListUniversity = new ArrayList<>();
        arrayListType = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();
        arrayListspecialization = new ArrayList<>();
        arrayListPlace = new ArrayList<>();
        arrayListPartPlace = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_old_trainee, container, false);
        bindView();
        title.setText(getResources().getString(R.string.traineePart));
        ShowTrainee();
        arrayListType.clear();
        arrayListType.add("تدريب جامعي");
        arrayListType.add("تطوع");

//        ArrayList<TraineeModel> arrayList = new ArrayList<>();
//         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new AdapterOldTrainee(getActivity(), arrayList, new CustomItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
//            }
//        }, new AdapterOldTrainee.MyRecyclerViewListener() {
//            @Override
//            public void AddNew(View v, int position) {
//                showDialog(getActivity());
//
//            }
//
//            @Override
//            public void Training(View v, int position) {
//
//                Intent refresh = new Intent(getActivity(), TrainingActivity.class);
//                startActivity(refresh);
//            }
//        });
//
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
        loading = view.findViewById(R.id.loading);

    }


    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog);
        FontButtonRegular save = dialog.findViewById(R.id.save);

        upload = dialog.findViewById(R.id.upload);
        imageView = dialog.findViewById(R.id.image);


        chooseUniversity = dialog.findViewById(R.id.university);
        chooseFaculty = dialog.findViewById(R.id.faculty);
        chooseType = dialog.findViewById(R.id.type);
        choosePlace = dialog.findViewById(R.id.place);
        choosespecialization = dialog.findViewById(R.id.specialization);
        choosePartPlace = dialog.findViewById(R.id.partPlace);


        universityList = dialog.findViewById(R.id.universitySpinner);
        typeList = dialog.findViewById(R.id.typeSpinner);
        facultyList = dialog.findViewById(R.id.facultySpinner);
        specializationList = dialog.findViewById(R.id.specializationSpinner);
        placeList = dialog.findViewById(R.id.placeSpinner);
        partPlaceList = dialog.findViewById(R.id.partPlaceSpinner);

        listUniversityLayout = dialog.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = dialog.findViewById(R.id.facultySpinnerLayout);
        listTypeLayout = dialog.findViewById(R.id.typeSpinnerLayout);
        listspecializationLayout = dialog.findViewById(R.id.specializationSpinnerLayout);
        listPlaceLayout = dialog.findViewById(R.id.placeSpinnerLayout);
        listPartPlaceLayout = dialog.findViewById(R.id.partPlaceSpinnerLayout);

        imageFaculty = dialog.findViewById(R.id.facultyImage);
        imageType = dialog.findViewById(R.id.iconType);
        imagespecialization = dialog.findViewById(R.id.specImage);
        imageUniversity = dialog.findViewById(R.id.imageUniversity);
        imagePlace = dialog.findViewById(R.id.imagePlace);
        imagePartPlace = dialog.findViewById(R.id.imagePartPlace);

        titleSpinnerFaculty = dialog.findViewById(R.id.facultyText);
        titleSpinnerUniversity = dialog.findViewById(R.id.textUniversity);
        titleSpinnerspecialization = dialog.findViewById(R.id.textSpec);
        titleSpinnerType = dialog.findViewById(R.id.textType);
        titleSpinnerPlace = dialog.findViewById(R.id.textPlace);
        titleSpinnerPartPlace = dialog.findViewById(R.id.textPartPlace);

        studentLayout = dialog.findViewById(R.id.studentLayout);
        numberHour = dialog.findViewById(R.id.hourNumber);

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


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validate()) {



//                    AddItem(new Trainee(name.getText().toString(),genderText,mobileNumber.getText().toString(),
//                            phoneNumber.getText().toString(),email.getText().toString(),arrayListUniversity.get(universityNum-1).getUniversiy_id()+"",
//                            arrayListFaculty.get(facultyNum-1).getCollage_id()+"",arrayListspecialization.get(specializationNum-1)+""
//                    ,arrayListType.get(typeNum-1)));

                } else {
                    Alarm("لم تتم إضافة جميع البيانات اللازمة");

                }


            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Matisse.from(OldTraineeFragment.this)
                        .choose(MimeType.of(MimeType.JPEG))
                        .countable(true)
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new MyGlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

            }
        });
        dialog.show();

    }

    public void ShowTrainee() {

        new UserAPI().getTrainee(ApplicationController.getInstance().getLoginUser().getUser_id()+ "",ApplicationController.getInstance().getLoginUser().getRole_id()+"",
                "1","1",
                new UniversalCallBack() {
                    @Override
                    public void onResponse(Object result) {

                        TraineeListModel traineeListModel = (TraineeListModel) result;

//                if (responseCategories.isStatus()) {
                        loading.setVisibility(View.GONE);

                        arrayList.clear();
                        arrayList.addAll(traineeListModel.getResult());
                        adapter = new AdapterOldTrainee(getActivity(), arrayList, new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new AdapterOldTrainee.MyRecyclerViewListener() {

                            @Override
                            public void AddNew(View v, int position) {
                                showDialog(getActivity());

                            }

                            @Override
                            public void Training(View v, int position) {

                                Intent refresh = new Intent(getActivity(), TrainingActivity.class);
                                startActivity(refresh);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(llm);
                        adapter.notifyDataSetChanged();

                        //                }
                    }

                    @Override
                    public void onFailure(Object result) {
                        if (result != null) {
                            ResponseError responseError = (ResponseError) result;
                            if (getActivity() != null) {

                                Alarm(getString(R.string.noAdd));
                                loading.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void OnError(String message) {

                        if (getActivity() != null) {
                            Alarm(getString(R.string.noInternet));
                            loading.setVisibility(View.GONE);
                        }
                    }
                });
    }


    public void AddItem(final AntherTraining item) {

        new UserAPI().AddAntherTrainingTrainee(item, new UniversalCallBack() {
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
                    Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null)
                    Alarm(getResources().getString(R.string.noInternet));

            }
        });
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


    public void Alarm(String message) {
        Alerter.create(getActivity())
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }

    public boolean Validate() {

        if (TextUtils.isEmpty(numberHour.getText().toString())){
            return false;
        } else if (typeNum < 1) {
            return false;
        } else if (typeNum == 1) {
            if (specializationNum > 1) {
                return false ;
            } else if (universityNum < 1) {
                return false;
            } else if (facultyNum < 1) {
                return false;
            }
        }
        return true;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            SelectedImage = Matisse.obtainResult(data).get(0);

//            UploadImage(SelectedImage);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.add_user);
            requestOptions.error(R.drawable.delete_user);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Log.d("selectAll", SelectedImage + "");

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(SelectedImage).thumbnail(.1f).into(imageView);


        }


    }

}
