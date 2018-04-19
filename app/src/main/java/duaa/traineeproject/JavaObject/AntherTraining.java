package duaa.traineeproject.JavaObject;

import java.io.Serializable;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class AntherTraining implements Serializable {
    int id;
    int type ;
    int student_number;
    int idUniversity;
    int idFaculty ;
    int idSpecification;
    String hourNumber;

    public AntherTraining(int id, int type, int student_number, int idUniversity, int idFaculty, int idSpecification, String hourNumber) {
        this.id = id;
        this.type = type;
        this.student_number = student_number;
        this.idUniversity = idUniversity;
        this.idFaculty = idFaculty;
        this.idSpecification = idSpecification;
        this.hourNumber = hourNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        this.student_number = student_number;
    }

    public int getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public int getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(int idSpecification) {
        this.idSpecification = idSpecification;
    }

    public String getHourNumber() {
        return hourNumber;
    }

    public void setHourNumber(String hourNumber) {
        this.hourNumber = hourNumber;
    }
}
