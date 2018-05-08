package duaa.traineeproject.JavaObject;

import java.io.Serializable;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class
AntherTraining implements Serializable {

    String trainee_id;
    String university;
    String collage;
    String specialization;
    String trainee_type;
    String university_number;
    String hour_number;
    String trainee_place;
    String place_partment;
    //    String ext;
//    String dir;
//    String source;
    String user_id;

    public AntherTraining(String trainee_id, String university, String collage, String specialization, String trainee_type, String university_number, String hour_number, String trainee_place, String place_partment, String user_id) {
        this.trainee_id = trainee_id;
        this.university = university;
        this.collage = collage;
        this.specialization = specialization;
        this.trainee_type = trainee_type;
        this.university_number = university_number;
        this.hour_number = hour_number;
        this.trainee_place = trainee_place;
        this.place_partment = place_partment;
        this.user_id = user_id;
    }

    public String getTrainee_id() {
        return trainee_id;
    }

    public void setTrainee_id(String trainee_id) {
        this.trainee_id = trainee_id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getTrainee_type() {
        return trainee_type;
    }

    public void setTrainee_type(String trainee_type) {
        this.trainee_type = trainee_type;
    }

    public String getUniversity_number() {
        return university_number;
    }

    public void setUniversity_number(String university_number) {
        this.university_number = university_number;
    }

    public String getHour_number() {
        return hour_number;
    }

    public void setHour_number(String hour_number) {
        this.hour_number = hour_number;
    }

    public String getTrainee_place() {
        return trainee_place;
    }

    public void setTrainee_place(String trainee_place) {
        this.trainee_place = trainee_place;
    }

    public String getPlace_partment() {
        return place_partment;
    }

    public void setPlace_partment(String place_partment) {
        this.place_partment = place_partment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
