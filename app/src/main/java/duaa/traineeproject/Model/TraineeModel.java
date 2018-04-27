package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class TraineeModel implements Serializable {
    int trainee_id;
    int id_number;
    String gender;
    String trainee_name;
    String email;
    String phone;
    String mobile;
    int is_deleted;
    int approve;
    int created;
    int created_by;
    String type_training;

    public TraineeModel(int trainee_id, int id_number, String gender, String trainee_name, String email, String phone, String mobile, int is_deleted, int approve, int created, int created_by, String type_training) {
        this.trainee_id = trainee_id;
        this.id_number = id_number;
        this.gender = gender;
        this.trainee_name = trainee_name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.is_deleted = is_deleted;
        this.approve = approve;
        this.created = created;
        this.created_by = created_by;
        this.type_training = type_training;
    }

    public int getTrainee_id() {
        return trainee_id;
    }

    public void setTrainee_id(int trainee_id) {
        this.trainee_id = trainee_id;
    }

    public int getId_number() {
        return id_number;
    }

    public void setId_number(int id_number) {
        this.id_number = id_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTrainee_name() {
        return trainee_name;
    }

    public void setTrainee_name(String trainee_name) {
        this.trainee_name = trainee_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getType_training() {
        return type_training;
    }

    public void setType_training(String type_training) {
        this.type_training = type_training;
    }
}
