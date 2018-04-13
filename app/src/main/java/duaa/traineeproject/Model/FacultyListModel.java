package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 12/04/18.
 */

public class FacultyListModel implements Serializable {

    @SerializedName("result")

    private List<Faculty> result ;

    public FacultyListModel(List<Faculty> result) {
        this.result = result;
    }

    public List<Faculty> getResult() {
        return result;
    }

    public void setResult(List<Faculty> result) {
        this.result = result;
    }
}
