package duaa.traineeproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class  ApplicationController extends Application {

    private static ApplicationController mInstance;
    private static Context context;
    Locale myLocale;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        mInstance = this;
        SharedPrefSingleton.init(this);
        VolleySingleton.getInstance();
//        loadLocale();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static synchronized ApplicationController getInstance() {
        if (mInstance == null) {
            return mInstance = new ApplicationController();
        } else
            return mInstance;
    }


    /**
     * Turn drawable resource into byte array.
     *
     * @param context parent context
     * @param id      drawable resource id
     * @return byte array
     */
    public static byte[] getFileDataFromDrawable(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

//    /**
//     * Turn drawable into byte array.
//     *
//     * @param drawable data
//     * @return byte array
//     */
//    public static byte[] getFileDataFromDrawable(Context context, Drawable drawable) {
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }


//    public static byte[] getStringFromInputStream(InputStream is) {
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//        byte[] bReturn = new byte[0];
//
//        String line;
//        try {
//
//            br = new BufferedReader(new InputStreamReader(is, "Big5"));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            String sContent = sb.toString();
//            bReturn = sContent.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return bReturn;
//    }

//    public static byte[] readBytes(Uri uri) throws IOException {
//        // this dynamically extends to take the bytes you read
//        InputStream inputStream = getAppContext().getContentResolver().openInputStream(uri);
//        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//
//        // this is storage overwritten on each iteration with bytes
//        int bufferSize = 1024;
//        byte[] buffer = new byte[bufferSize];
//
//        // we need to know how may bytes were read to write them to the byteBuffer
//        int len = 0;
//        while ((len = inputStream.read(buffer)) != -1) {
//            byteBuffer.write(buffer, 0, len);
//        }
//
//        // and then we can return your byte array.
//        return byteBuffer.toByteArray();
//    }
    public static Context getAppContext() {
        return ApplicationController.context;
    }


//    public User getUser() {
//        return Realm.getDefaultInstance().where(User.class).findFirst();
//    }

//    public void loginUser(final User user) {
//        SharedPreferences.Editor editor = ApplicationController.getAppContext().getSharedPreferences("access_token", Context.MODE_PRIVATE).edit();
//        editor.putString("access_token", user.getAccess_token());
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        editor.putString("User", json);
//
//
//        editor.commit();
//        if (Realm.getDefaultInstance().where(User.class).findFirst() == null) {
//
//            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealm(user);
//                }
//            });
//        } else {
//            logout();
//            loginUser(user);
//        }
//    }

//    public void logout() {
//        Logout(ApplicationController.getInstance().getUser().getAccess_token());
//        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(User.class);
//            }
//        });
//    }


//    public boolean IsUserLoggedIn() {
//        if (Realm.getDefaultInstance().where(User.class).findFirst() == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }

//    public void RefreshToken(ResponseToken responseToken) {
//        User toEdit = Realm.getDefaultInstance().where(User.class)
//                .findFirst();
//        Realm.getDefaultInstance().beginTransaction();
//        toEdit.setAccess_token(responseToken.getAccess_token());
//        toEdit.setRefresh_token(responseToken.getRefresh_token());
//        toEdit.setExpires_in(responseToken.getExpires_in());
//        toEdit.setToken_type(responseToken.getToken_type());
//        Realm.getDefaultInstance().commitTransaction();
//    }


//    public void Logout() {
//        logout();
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

//    public void Logout(final String token) {
//        new UserAPI().Logout(token, new UniversalCallBack() {
//            @Override
//            public void onResponse(Object UniversityListModel) {
//
//            }
//
//            @Override
//            public void onFailure(Object UniversityListModel) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void OnError(String message) {
//
//            }
//        });
//    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "ar");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }
}