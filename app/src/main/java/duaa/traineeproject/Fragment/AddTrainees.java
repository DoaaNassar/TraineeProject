package duaa.traineeproject.Fragment;

import android.app.NativeActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

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
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.MyCustomAnimation;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;
import duaa.traineeproject.view.MyGlideEngine;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

public class AddTrainees extends Fragment {

    View view;
    Uri SelectedImage;
    ImageView imageView, upload;
    private static final int REQUEST_CODE_CHOOSE = 500;
    ArrayList<University> arrayListUniversity;
    ListView universityList;
    LinearLayout listUniversityLayout ,listTypeLayout ,listFacultyLayout ,listSpecificationLayout;
    ImageView imageUniversity ,imageType ,imageFaculty ,imageSpecification;
    FontTextViewRegular titleSpinnerType,titleSpinnerFaculty,titleSpinnerSpecification,titleSpinnerUniversity ;
    int height;
    FontEditTextViewRegular name ,idNumber ,mobileNumber,phoneNumber,studentID,address , email ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        arrayListUniversity = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_trainees, container, false);
        bindView();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("selectAll", SelectedImage + "");
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

        universityList = view.findViewById(R.id.universitySpinner);

        listUniversityLayout = view.findViewById(R.id.universitySpinnerLayout);
        listFacultyLayout = view.findViewById(R.id.facultySpinnerLayout);
        listTypeLayout = view.findViewById(R.id.type);
        listSpecificationLayout = view.findViewById(R.id.specificationSpinnerLayout);

        imageFaculty = view.findViewById(R.id.facultyImage);
        imageType = view.findViewById(R.id.iconType);
        imageSpecification = view.findViewById(R.id.iconSpec);
        imageUniversity = view.findViewById(R.id.imageUniversity);

        titleSpinnerFaculty = view.findViewById(R.id.facultyText);
        titleSpinnerSpecification = view.findViewById(R.id.specText);
        titleSpinnerUniversity = view.findViewById(R.id.textUniversity);
        titleSpinnerType = view.findViewById(R.id.textType);


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
        }
        return true;
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
                        Alerter.create(getActivity())
                                .setText(responseError.getMessage())
                                .hideIcon()
                                .setBackgroundColorRes(R.color.colorPrimary)
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
                        .show();


            }
        });
//
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