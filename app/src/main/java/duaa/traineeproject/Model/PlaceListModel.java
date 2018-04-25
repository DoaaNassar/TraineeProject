package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 19/04/18.
 */

public class PlaceListModel  implements Serializable {

    @SerializedName("result")

    private List<PlaceModel> result ;

    public PlaceListModel(List<PlaceModel> result) {
        this.result = result;
    }

    public List<PlaceModel> getResult() {
        return result;
    }

    public void setResult(List<PlaceModel> result) {
        this.result = result;
    }
}
