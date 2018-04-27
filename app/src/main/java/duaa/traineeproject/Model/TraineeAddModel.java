package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 26/04/18.
 */

public class TraineeAddModel implements Serializable

{
    private String message;
    private boolean status;
    private String trainee_data_id ;


    public TraineeAddModel() {
    }

    public TraineeAddModel(String message, boolean status, String trainee_data_id) {
        this.message = message;
        this.status = status;
        this.trainee_data_id = trainee_data_id;
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

    public String getTrainee_data_id() {
        return trainee_data_id;
    }

    public void setTrainee_data_id(String trainee_data_id) {
        this.trainee_data_id = trainee_data_id;
    }
}
