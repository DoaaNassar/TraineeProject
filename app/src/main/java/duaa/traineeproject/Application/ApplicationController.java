package duaa.traineeproject.Application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import duaa.traineeproject.API.SharedPrefSingleton;
import duaa.traineeproject.API.VolleySingleton;
import duaa.traineeproject.Model.showUserLogin;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class  ApplicationController extends Application {

    private static ApplicationController mInstance;
    private static Context context;
    SharedPreferences sharedPreferences ;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sharedPreferences = getSharedPreferences("db",MODE_PRIVATE);

        mInstance = this;
        SharedPrefSingleton.init(this);
        VolleySingleton.getInstance();


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


    public static Context getAppContext() {
        return ApplicationController.context;
    }


    public String token(){
        SharedPreferences sharedPreferences = getSharedPreferences("app_data",MODE_PRIVATE);
        String token = sharedPreferences.getString("access_token","");
        return token ;

    }


    public  void  login_token(String access_token){

        SharedPreferences.Editor editor = ApplicationController.getAppContext().
                getSharedPreferences("app_data", Context.MODE_PRIVATE).edit();
        editor.putString("access_token", access_token);
        editor.commit();

    }

    public  void userLogin(final showUserLogin user){

        SharedPreferences.Editor editor = ApplicationController.getAppContext().
                getSharedPreferences("app_data", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();

    }

    public  showUserLogin getLoginUser (){
        SharedPreferences sharedPreferences = getSharedPreferences("app_data",MODE_PRIVATE);
        String response = sharedPreferences.getString("User","");
        Gson gson = new Gson();
        showUserLogin user =gson.fromJson(response ,showUserLogin.class);

        return user;
    }

}