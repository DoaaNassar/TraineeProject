package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 04/04/18.
 */

public class AddUniversityObject implements Serializable {

    String university_name;
    String email;
    String mobile;
    String phone;
    String full_address ;
//    String ext;
//    String dir ;


    public AddUniversityObject(String university_name, String email, String mobile, String phone, String full_address) {
        this.university_name = university_name;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.full_address = full_address;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }
}
