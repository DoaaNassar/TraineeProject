package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class TraineeListModel implements Serializable {
    @SerializedName("result")

    private List<TraineeModel> result ;

    public TraineeListModel(List<TraineeModel> result) {
        this.result = result;
    }

    public List<TraineeModel> getResult() {
        return result;
    }

    public void setResult(List<TraineeModel> result) {
        this.result = result;
    }
}
