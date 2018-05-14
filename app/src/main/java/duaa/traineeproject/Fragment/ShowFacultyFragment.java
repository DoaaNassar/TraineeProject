package duaa.traineeproject.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Adapter.AdapterSpinner;
import duaa.traineeproject.Adapter.AdapterSpinnerFaculty;
import duaa.traineeproject.Adapter.FacultyAdapter;
import duaa.traineeproject.Adapter.SpecAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.Units.UIUtils;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class ShowFacultyFragment extends Fragment {

    View view;
    List<String> specializationList;
    List<Faculty> arrayListFaculty;
    RecyclerView recyclerView;
    SpecAdapter specAdapter;
    FontTextViewRegular title;
    int height;
    Typeface face;
    FacultyAdapter facultyAdapter;
    LinearLayout loading;
    FontTextViewRegular facultyText;
    ImageView search ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specializationList = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();

        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_faculty, container, false);
        bindView();
        title.setText(getString(R.string.universityPart));

        FacultyItems();
        search.setVisibility(View.VISIBLE);

        return view;
    }

    public void bindView() {

        recyclerView = view.findViewById(R.id.recyclerview);
        title = getActivity().findViewById(R.id.title);
        loading = view.findViewById(R.id.loading);
        facultyText = getActivity().findViewById(R.id.faculty);
        search = getActivity().findViewById(R.id.search);


    }

    public void FacultyItems() {

        new UserAPI().getAllFaculty("", new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                FacultyListModel facultyListModel = (FacultyListModel) result;

//                if (responseCategories.isStatus()) {
                loading.setVisibility(View.GONE);
                facultyText.setEnabled(true);

                arrayListFaculty.clear();
                arrayListFaculty.addAll(facultyListModel.getResult());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                facultyAdapter = new FacultyAdapter(getActivity(), arrayListFaculty,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new FacultyAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(facultyAdapter);
                facultyAdapter.notifyDataSetChanged();

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
