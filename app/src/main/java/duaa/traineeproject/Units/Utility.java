package duaa.traineeproject.Units;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class Utility {

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    public static void hideSoftKeyboard(Activity activity) {

        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public static String getRealPathFromURI(Context context, Uri contentURI, String type) {

        String result  = null;
        try {
            Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) { // Source is Dropbox or other similar local file path
                result = contentURI.getPath();
                Log.d("TAG", "result******************" + result);
            } else {
                cursor.moveToFirst();
                int idx = 0;
                if(type.equalsIgnoreCase("IMAGE")){
                    idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                }else if(type.equalsIgnoreCase("VIDEO")){
                    idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
                }else if(type.equalsIgnoreCase("AUDIO")){
                    idx = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
                }
                result = cursor.getString(idx);
                Log.d("TAG", "result*************else*****" + result);
                cursor.close();
            }
        } catch (Exception e){
            Log.e("TAG", "Exception ",e);
        }
        return result;
    }

    public static byte[] convertBitmapToByte(Bitmap bmp) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;
    }

    /**
     * method to convert pixel to dp
     *
     * @param px this is  parameter for pxToDp  method
     * @return return value
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


}
