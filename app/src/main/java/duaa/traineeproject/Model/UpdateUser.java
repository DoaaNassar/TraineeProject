package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 02/05/18.
 */

public class UpdateUser implements Serializable {
    String user_name;
    String email;
    String phone ;
    String mobile;

    public UpdateUser(String user_name, String email, String phone, String mobile) {
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
}
