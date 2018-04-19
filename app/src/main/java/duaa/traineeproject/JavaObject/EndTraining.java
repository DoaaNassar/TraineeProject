package duaa.traineeproject.JavaObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class EndTraining implements Serializable {
    List<Integer> list ;

    public EndTraining(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
