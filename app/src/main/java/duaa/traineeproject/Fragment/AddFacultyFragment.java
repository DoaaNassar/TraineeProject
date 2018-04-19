package duaa.traineeproject.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.Adapter.ShowUniversityAdapter;
import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.AddFacultyModel;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;


public class AddFacultyFragment extends Fragment {
    View view;
    SpecAdapter specAdapter;
    List<String> specificationList;
    ArrayList<University> unviersityList;
    FontButtonRegular addSpec, save;
    RecyclerView recyclerView;
    FontEditTextViewRegular spec, facultyName;
    FontTextViewRegular title;
    Typeface face;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specificationList = new ArrayList<>();
        unviersityList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_faculty, container, false);
        bindView();
        title.setText("قسم الجامعات والكليات");

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        specAdapter = new SpecAdapter(getActivity(), specificationList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new SpecAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
                specificationList.remove(position);
                specAdapter.notifyItemRemoved(position);
                specAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(specAdapter);
        specAdapter.notifyDataSetChanged();
        addSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(spec.getText().toString())) {
                    specificationList.add(spec.getText().toString());
                    specAdapter.notifyDataSetChanged();
                } else {
                    Alarm("أضف تخصصاً");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(facultyName.getText().toString())) {
                    if (specificationList.size() != 0) {
                        AddItem(new AddFacultyModel(facultyName.getText().toString(), specificationList));
                    } else
                        Alarm("يجب إضافة قسم واحد على الأقل ");
                    } else {
                        Alarm("يجب إضافة اسم الكلية ");

                }

            }
        });

        return view;
    }


    public void bindView() {
        addSpec = view.findViewById(R.id.addSpec);
        recyclerView = view.findViewById(R.id.specificationList);
//        universityRecyclerView=view.findViewById(R.id.universityList);
        spec = view.findViewById(R.id.textSpec);
        title = getActivity().findViewById(R.id.title);
        save = view.findViewById(R.id.save);
        facultyName = view.findViewById(R.id.nameFaculty);

    }

    public void AddItem(final AddFacultyModel item) {

        isBack = true;
        new UserAPI().AddFaculty(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseTrue responseItem = (ResponseTrue) result;

//                if (responseItem.isStatus()) {
                    Alarm(responseItem.getMessage());

//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm("لا يوجد انترنت");

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                Alarm("لا يوجد انترنت");

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
