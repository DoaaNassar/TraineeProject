package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 28/03/18.
 */

public class Trainer implements Serializable {

    int trainer_id;
    String trainer_name;
    String email;
    String phone ;
    String mobile ;
    int is_deleted ;

    public Trainer(int trainer_id, String trainer_name, String email, String phone, String mobile, int is_deleted) {
        this.trainer_id = trainer_id;
        this.trainer_name = trainer_name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.is_deleted = is_deleted;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
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
}
