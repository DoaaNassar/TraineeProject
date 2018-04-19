package duaa.traineeproject.Model;

import java.io.Serializable;
import java.util.List;

import duaa.traineeproject.JavaObject.Specification;

/**
 * Created by AL-Qema on 13/04/18.
 */

public class AddFacultyModel implements Serializable {
    String name ;
    List <String> specifications ;

    public AddFacultyModel(String name, List<String> specifications) {
        this.name = name;
        this.specifications = specifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<String> specifications) {
        this.specifications = specifications;
    }
}
