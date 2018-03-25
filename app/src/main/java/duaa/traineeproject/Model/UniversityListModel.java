package duaa.traineeproject.Model;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 09/03/18.
 */

public class UniversityListModel implements Serializable {
    @SerializedName("result")

   private List<University> result ;

    public UniversityListModel(List<University> result) {
        this.result = result;
    }

    public List<University> getResult() {
        return result;
    }

    public void setResult(List<University> result) {
        this.result = result;
    }
}
