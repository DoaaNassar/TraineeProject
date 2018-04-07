package duaa.traineeproject.Fragment;

import android.app.NativeActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import duaa.traineeproject.R;
import duaa.traineeproject.view.FontButtonRegular;
import duaa.traineeproject.view.MyGlideEngine;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

public class AddTrainees extends Fragment {

    View view;
    Uri SelectedImage;
    ImageView imageView ,upload;
    private static final int REQUEST_CODE_CHOOSE = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_trainees, container, false);
        bindView();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("selectAll",SelectedImage+"");
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


public void bindView(){
        upload = view.findViewById(R.id.upload);
        imageView = view.findViewById(R.id.image);
}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ffffff",requestCode+"123456");

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            SelectedImage = Matisse.obtainResult(data).get(0);
            //user_img.setImageURI(SelectedImage);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.add_user);
            requestOptions.error(R.drawable.delete_user);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//            requestOptions.apply(RequestOptions.bitmapTransform(new CropCircleTransformation()));

            Log.d("selectAll",SelectedImage+"");

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(SelectedImage).thumbnail(.1f).into(imageView);
            imageView.setVisibility(View.VISIBLE);


        }

    }



}