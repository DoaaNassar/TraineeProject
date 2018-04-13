package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import duaa.traineeproject.JavaObject.Specification;

/**
 * Created by AL-Qema on 12/04/18.
 */

public class Faculty implements Serializable {
    private int faculty_id ;
    private String faculty_name;
    private List<Specification> specification_list ;

    public Faculty(int faculty_id, String faculty_name, List<Specification> specification_list) {
        this.faculty_id = faculty_id;
        this.faculty_name = faculty_name;
        this.specification_list = specification_list;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public List<Specification> getSpecification_list() {
        return specification_list;
    }

    public void setSpecification_list(List<Specification> specification_list) {
        this.specification_list = specification_list;
    }
}
