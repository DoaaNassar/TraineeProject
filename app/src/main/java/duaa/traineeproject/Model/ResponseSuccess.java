package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class ResponseSuccess implements Serializable

{
    private String message;
    private boolean status;


    public ResponseSuccess() {
    }

    public ResponseSuccess(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
