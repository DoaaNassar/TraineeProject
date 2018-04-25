package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class PartPlaceListModel implements Serializable {
    @SerializedName("result")

    private List<PartPlaceModel> result ;

    public PartPlaceListModel(List<PartPlaceModel> result) {
        this.result = result;
    }

    public List<PartPlaceModel> getResult() {
        return result;
    }

    public void setResult(List<PartPlaceModel> result) {
        this.result = result;
    }
}
