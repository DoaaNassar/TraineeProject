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
import duaa.traineeproject.JavaObject.specialization;
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
    ArrayList<University> arrayListUniversity;
    ArrayList<Faculty> arrayListFaculty;
    ImageView imageUniversity, imageFaculty;
    Typeface face;
    FontTextViewRegular title;
    int universityNum ,facultyNum ;
    FontTextViewRegular titleSpinnerUniversity  , titleSpinnerFaculty;
    ImageView search ;


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
        search.setVisibility(View.GONE);



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

                    Alarm("يرجى تعبئة باقي البيانات");
                }

            }
        });

        chooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listUniversityLayout.getVisibility() == View.GONE) {
                    UniversityItem();
                }
                SpinnerAnimation(listUniversityLayout, imageUniversity);
                SpinnerAnimationClose(listFacultyLayout, imageFaculty);

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
                    SpinnerAnimationClose(listUniversityLayout, imageUniversity);

                } else {
                    Alarm("أضف الجامعة أولا ");
                }
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



        facultyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                facultyNum = position + 1;
                titleSpinnerFaculty.setText(arrayListFaculty.get(position).getCollage_name());
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
        } else if (universityNum==0) {
            return false;
        } else if (facultyNum==0) {
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

        titleSpinnerFaculty = view.findViewById(R.id.facultyText);
        titleSpinnerUniversity = view.findViewById(R.id.textUniversity);

        universityList = view.findViewById(R.id.universitySpinner);
        facultyList = view.findViewById(R.id.facultySpinner);

        imageUniversity = view.findViewById(R.id.imageUniversity);
        imageFaculty = view.findViewById(R.id.faculty);

        title = getActivity().findViewById(R.id.title);
        search = getActivity().findViewById(R.id.search);



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

    ////////////////POST

    public void AddItem(final TrainerObject item) {

        loadingLayout.setVisibility(View.VISIBLE);
        contentLayout.setEnabled(false);
        isBack = true;

        new UserAPI().AddTrainer(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseSuccess responseItem = (ResponseSuccess) result;

                if (responseItem.isStatus()) {

                    Alarm(responseItem.getMessage());
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    if (getActivity() != null) {
                        Alarm(getResources().getString(R.string.noAdd));
                        loadingLayout.setVisibility(View.GONE);
                        contentLayout.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                if (getActivity() != null) {
                    Alarm(getResources().getString(R.string.noInternet));
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setEnabled(true);
                }

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

    public void SpinnerAnimationClose(LinearLayout view2, ImageView imageView) {
        MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
        height = a.getHeight();
        view2.startAnimation(a);
        imageView.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);

    }

}