package duaa.traineeproject.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class LoginModel implements Serializable {
   showUserLogin result;
    String message;
    int authrezed ;

    public LoginModel(showUserLogin result, String message , int authrezed) {
        this.result = result;
        this.message = message;
        this.authrezed =authrezed ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public showUserLogin getResult() {
        return result;
    }

    public void setResult(showUserLogin result) {
        this.result = result;
    }

    public int getAuthrezed() {
        return authrezed;
    }

    public void setAuthrezed(int authrezed) {
        this.authrezed = authrezed;
    }
}
