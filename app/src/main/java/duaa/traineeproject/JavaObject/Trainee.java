package duaa.traineeproject.JavaObject;

import java.io.Serializable;

/**
 * Created by AL-Qema on 10/03/18.
 */

public class Trainee implements Serializable{

    String name ;
    String gender ;
    String mobileNumber ;
    String phoneNumber ;
    String email ;
    String university;
    String collage;
    String specialization;
    String trainee_type;
    String university_number;
    String hour_number;
    String trainee_place;
    String place_partment;
    String id_num ;
//    String ext;
//    String dir;
//    String source;
    String role_id;
    String user_id;

    public Trainee(String name, String gender, String mobileNumber, String phoneNumber, String email, String university, String collage, String specialization, String trainee_type, String university_number, String hour_number, String trainee_place, String place_partment, String id_num, String role_id, String user_id) {
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.university = university;
        this.collage = collage;
        this.specialization = specialization;
        this.trainee_type = trainee_type;
        this.university_number = university_number;
        this.hour_number = hour_number;
        this.trainee_place = trainee_place;
        this.place_partment = place_partment;
        this.id_num = id_num;
        this.role_id = role_id;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }
}
