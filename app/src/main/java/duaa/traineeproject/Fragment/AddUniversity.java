package duaa.traineeproject.Fragment;

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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tapadoo.alerter.Alerter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.AddUniversityObject;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;
import duaa.traineeproject.view.MyGlideEngine;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;
import static duaa.traineeproject.Constants.FONTS_APP;
import static duaa.traineeproject.Page.TrainerFragment.isBack;

public class AddUniversity extends Fragment {

    ImageView upload, image;
    View view;
    Uri SelectedImage;
    FontEditTextViewRegular nameUniversity , address ,email ,phoneNumber , mobileNumber;
    FontButtonRegular save ;
    FontTextViewRegular title ;
    Typeface face;


    private static final int REQUEST_CODE_CHOOSE = 600;
    FrameLayout loadingLayout;
    LinearLayout contentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_university, container, false);
        bindView();
        title.setText(getString(R.string.universityPart));


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("selectAll", SelectedImage + "");
                Matisse.from(AddUniversity.this)
                        .choose(MimeType.of(MimeType.JPEG))
                        .countable(true)
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new MyGlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    String nameUniversityTxt = nameUniversity.getText().toString();
                    String addressTxt = address.getText().toString();
                    String mobileNumberTxt = mobileNumber.getText().toString();
                    String phoneNumberTxt = phoneNumber.getText().toString();
                    String emailTxt = email.getText().toString();
                    AddItem(new AddUniversityObject(nameUniversityTxt, emailTxt, mobileNumberTxt, phoneNumberTxt, addressTxt));
                }else {

                    Alarm("يرجى تعبئة جميع البيانات اللازمة :)");
                }
            }
        });


        return view;
    }

    public void bindView() {
        upload = view.findViewById(R.id.upload);
        image = view.findViewById(R.id.image);
        nameUniversity = view.findViewById(R.id.nameUniversity);
        address = view .findViewById(R.id.address);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        email = view.findViewById(R.id.email);
        save = view.findViewById(R.id.save);
//        contentLayout = getActivity().findViewById(R.id.contentLayout);
        loadingLayout = getActivity().findViewById(R.id.loadingLayout);
        title = getActivity().findViewById(R.id.title);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ffffff", requestCode + "123456");

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            SelectedImage = Matisse.obtainResult(data).get(0);
            //user_img.setImageURI(SelectedImage);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.add_user);
            requestOptions.error(R.drawable.delete_user);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//            requestOptions.apply(RequestOptions.bitmapTransform(new CropCircleTransformation()));

            Log.d("selectAll", SelectedImage + "");

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(SelectedImage).thumbnail(.1f).into(image);

            image.setVisibility(View.VISIBLE);
        }

    }


    public void AddItem(final AddUniversityObject item) {
        loadingLayout.setVisibility(View.VISIBLE);
        isBack = true ;
//        contentLayout.setEnabled(false);
        new UserAPI().AddUniversity(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseTrue responseItem = (ResponseTrue) result;
                String message = responseItem.getMessage();

                loadingLayout.setVisibility(View.GONE);
                isBack = false ;

//                contentLayout.setEnabled(true);
                Toast.makeText(getActivity(), message+"", Toast.LENGTH_SHORT).show();
//                if (responseItem.isStatus()) {
//                    loadingLayout.setVisibility(View.VISIBLE);
//                    contentLayout.setEnabled(false);
//
//                }

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    isBack = false ;
                    loadingLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFinish() {
                loadingLayout.setVisibility(View.GONE);
                isBack = false ;

            }

            @Override
            public void OnError(String message) {
//
                if(getActivity()!= null){
                    Alarm(getResources().getString(R.string.noInternet));
                    isBack = false ;

                }
            }
        });
    }


    public boolean Validate() {

        if (TextUtils.isEmpty(nameUniversity.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(mobileNumber.getText().toString())) {
            return false;
        }
        return true;
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
