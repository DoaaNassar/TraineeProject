package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 13/04/18.
 */

public class OldTrainerListModel  implements Serializable{
    @SerializedName("result")

    private List<Trainer> result ;

    public OldTrainerListModel(List<Trainer> result) {
        this.result = result;
    }

    public List<Trainer> getResult() {
        return result;
    }

    public void setResult(List<Trainer> result) {
        this.result = result;
    }
}
