package duaa.traineeproject.Model;

import java.io.Serializable;
import java.util.List;


/**
 * Created by AL-Qema on 13/04/18.
 */

public class AddFacultyModel implements Serializable {
    String name ;
    List <String> specializations ;

    public AddFacultyModel(String name, List<String> specializations) {
        this.name = name;
        this.specializations = specializations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getspecializations() {
        return specializations;
    }

    public void setspecializations(List<String> specializations) {
        this.specializations = specializations;
    }
}
