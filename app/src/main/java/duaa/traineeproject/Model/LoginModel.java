package duaa.traineeproject.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class LoginModel implements Serializable {
int  authrezed ;
String message ;
List<showUserLogin>result ;

    public LoginModel(int authrezed, String message, List<showUserLogin> result) {
        this.authrezed = authrezed;
        this.message = message;
        this.result = result;
    }

    public int getAuthrezed() {
        return authrezed;
    }

    public void setAuthrezed(int authrezed) {
        this.authrezed = authrezed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<showUserLogin> getResult() {
        return result;
    }

    public void setResult(List<showUserLogin> result) {
        this.result = result;
    }
}
