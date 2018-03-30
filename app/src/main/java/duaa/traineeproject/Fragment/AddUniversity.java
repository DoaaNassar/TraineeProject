package duaa.traineeproject.Fragment;

import android.content.Context;
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
import com.zhihu.matisse.engine.impl.PicassoEngine;

import duaa.traineeproject.R;

import static android.app.Activity.RESULT_OK;

public class AddUniversity extends Fragment {

    ImageView upload, image;
    View view;
    Uri SelectedImage;
    private static final int REQUEST_CODE_CHOOSE = 600;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_university, container, false);
        bindView();

        image.setVisibility(View.GONE);

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
                        .imageEngine(new PicassoEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

            }
        });
        return view;
    }

    public void bindView() {
        upload = view.findViewById(R.id.upload);
        image = view.findViewById(R.id.image);
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

}
