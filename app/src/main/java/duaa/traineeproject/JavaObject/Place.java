package duaa.traineeproject.JavaObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class Place implements Serializable {

    String name ;
    List<PartObject>part ;

    public Place(String name, List<PartObject> part) {
        this.name = name;
        this.part = part;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PartObject> getPart() {
        return part;
    }

    public void setPart(List<PartObject> part) {
        this.part = part;
    }
}
