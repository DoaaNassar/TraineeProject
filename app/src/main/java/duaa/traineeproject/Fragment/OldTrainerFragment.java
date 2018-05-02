package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ListView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.OldTrainerAdapter;
import duaa.traineeproject.Adapter.PlaceAdapter;
import duaa.traineeproject.Adapter.TrainerAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Trainee;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.OldTrainerListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.Model.TrainerListModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class OldTrainerFragment extends Fragment {

    View view;
    FontTextViewRegular title;
    RecyclerView recyclerView;
    OldTrainerAdapter oldTrainerAdapter;
    ArrayList<Trainer> arrayList;
    Typeface face;
    ImageView search;
    Dialog dialog ;
    FontEditTextViewRegular name, email, phoneNumber, mobileNumber;
    LinearLayout chooseUniversity, chooseFaculty;
    FontButtonRegular save;
    int height = 0;
    LinearLayout listUniversityLayout, listFacultyLayout;
    ListView universityList, facultyList;
    ArrayList<University> arrayListUniversity;
    ArrayList<Faculty> arrayListFaculty;
    ImageView imageUniversity, imageFaculty;

    int universityNum ,facultyNum ;
    FontTextViewRegular titleSpinnerUniversity  , titleSpinnerFaculty;
    FrameLayout loadingLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);
        arrayListFaculty =new ArrayList<>();
        arrayListUniversity = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_trainer, container, false);
        bindView();
        title.setText(getString(R.string.trainerPart));

        trainer();
        search.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        title = getActivity().findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
        search = getActivity().findViewById(R.id.search);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);


    }

    public void trainer() {

        new UserAPI().getAllNowTrainer("0", new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                TrainerListModel response = (TrainerListModel) result;

//                if (responseCategories.isStatus()) {
//                loading.setVisibility(View.GONE);
                arrayList.clear();
                arrayList.addAll(response.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                oldTrainerAdapter = new OldTrainerAdapter(getActivity(), arrayList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new OldTrainerAdapter.MyRecyclerViewListener() {

                    @Override
                    public void Enable(View v, int position) {
                        showDialog(getActivity(),arrayList.get(position));

                    }

                    @Override
                    public void Edit(View v, int position) {

                    }
                });

                recyclerView.setAdapter(oldTrainerAdapter);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                oldTrainerAdapter.notifyDataSetChanged();

            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    loadingLayout.setVisibility(View.GONE);

                    if (getActivity() != null)
                        Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {
                loadingLayout.setVisibility(View.GONE);

            }

            @Override
            public void OnError(String message) {
                loadingLayout.setVisibility(View.GONE);
                if (getActivity() != null)
                    Alarm(getResources().getString(R.string.noInternet));

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

    public void showDialog(Activity activity , Trainer trainer) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_edit);

        name = dialog.findViewById(R.id.name);
        email = dialog.findViewById(R.id.email);
        phoneNumber = dialog.findViewById(R.id.phoneNumber);
        mobileNumber = dialog.findViewById(R.id.mobileNumber);
        chooseUniversity = dialog.findViewById(R.id.chooseUniversity);
        chooseFaculty = dialog.findViewById(R.id.chooseFaculty);
        save = dialog.findViewById(R.id.save);

        listUniversityLayout = dialog.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = dialog.findViewById(R.id.facultySpinnerLayout);

        titleSpinnerFaculty = dialog.findViewById(R.id.facultyText);
        titleSpinnerUniversity = dialog.findViewById(R.id.textUniversity);

        universityList = dialog.findViewById(R.id.universitySpinner);
        facultyList = dialog.findViewById(R.id.facultySpinner);

        imageUniversity = dialog.findViewById(R.id.imageUniversity);
        imageFaculty = dialog.findViewById(R.id.faculty);


        name.setText(trainer.getTrainer_name());
        email.setText(trainer.getEmail());
        phoneNumber.setText(trainer.getPhone());
        mobileNumber.setText(trainer.getMobile());
//        titleSpinnerFaculty.setText(trainer.);

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



        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    String nameTxt = name.getText().toString();
                    String emailTxt = email.getText().toString();
                    String phoneTxt = phoneNumber.getText().toString();
                    String mobileTxt = mobileNumber.getText().toString();

//                    AddItem(new TrainerObject(nameTxt, emailTxt, mobileTxt, "5", arrayListUniversity.get(universityNum-1).getUniversiy_id()+""
//                            , phoneTxt, arrayListFaculty.get(facultyNum-1).getCollage_id()+""));

                } else {

                    Alarm("يرجى تعبئة باقي البيانات");
                }

            }
        });
        dialog.show();

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

    public void SpinnerAnimationClose(LinearLayout view2, ImageView imageView) {
        MyCustomAnimation a = new MyCustomAnimation(view2, 1000, MyCustomAnimation.COLLAPSE);
        height = a.getHeight();
        view2.startAnimation(a);
        imageView.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);

    }

    public boolean Validate() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(mobileNumber.getText().toString())) {
            return false;
        }
        return true;
    }
}
