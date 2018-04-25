package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class SpecializationListModel  implements Serializable {
    @SerializedName("result")

    private List<SpecializationModel> result ;


    public SpecializationListModel(List<SpecializationModel> result) {
        this.result = result;
    }

    public List<SpecializationModel> getResult() {
        return result;
    }

    public void setResult(List<SpecializationModel> result) {
        this.result = result;
    }
}
