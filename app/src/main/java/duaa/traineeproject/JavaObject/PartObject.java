package duaa.traineeproject.JavaObject;

import java.io.Serializable;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class PartObject implements Serializable{
String partment_name;
String num ;

    public PartObject(String partment_name, String num) {
        this.partment_name = partment_name;
        this.num = num;
    }

    public String getPartment_name() {
        return partment_name;
    }

    public void setPartment_name(String partment_name) {
        this.partment_name = partment_name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
