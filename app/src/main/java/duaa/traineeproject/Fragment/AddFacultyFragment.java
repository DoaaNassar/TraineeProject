package duaa.traineeproject.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.AddFacultyModel;

import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class AddFacultyFragment extends Fragment {
    View view;
    SpecAdapter specAdapter;
    List<String> specializationList;
    ArrayList<University> unviersityList;
    FontButtonRegular  save;
    ImageView addSpec;
    RecyclerView recyclerView;
    FontEditTextViewRegular spec, facultyName;
    FontTextViewRegular title;
    Typeface face;
    FrameLayout loadingLayout;
    ImageView search ;
    Dialog dialog ;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specializationList = new ArrayList<>();
        unviersityList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_faculty, container, false);
        bindView();
        title.setText("قسم الجامعات والكليات");
        search.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        specAdapter = new SpecAdapter(getActivity(), specializationList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }, new SpecAdapter.MyRecyclerViewListener() {
            @Override
            public void RemoveImage(View v, int position) {
                specializationList.remove(position);
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
                    specializationList.add(spec.getText().toString());
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
                    if (specializationList.size() != 0) {
                        AddItem(new AddFacultyModel(facultyName.getText().toString(), specializationList));
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
        recyclerView = view.findViewById(R.id.specializationList);
//        universityRecyclerView=view.findViewById(R.id.universityList);
        spec = view.findViewById(R.id.textSpec);
        title = getActivity().findViewById(R.id.title);
        save = view.findViewById(R.id.save);
        facultyName = view.findViewById(R.id.nameFaculty);
        search = getActivity().findViewById(R.id.search);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);



    }

    public void AddItem(final AddFacultyModel item) {
        loadingLayout.setVisibility(View.VISIBLE);
        showDialog(getActivity());

        new UserAPI().AddFaculty(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseTrue responseItem = (ResponseTrue) result;
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();
//                if (responseItem.isStatus()) {
                    Alarm(responseItem.getMessage());

//                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    Alarm(getResources().getString(R.string.noAdd));
                    loadingLayout.setVisibility(View.GONE);
                    dialog.hide();



                }
            }

            @Override
            public void onFinish() {
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();

            }

            @Override
            public void OnError(String message) {
                Alarm("لا يوجد انترنت");
                loadingLayout.setVisibility(View.GONE);
                dialog.hide();

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

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.diii);
        dialog.setCancelable(false);

        dialog.show();

    }

}
