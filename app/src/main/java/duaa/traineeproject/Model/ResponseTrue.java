package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 04/04/18.
 */

public class ResponseTrue implements Serializable {
    String message ;

    public ResponseTrue(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
