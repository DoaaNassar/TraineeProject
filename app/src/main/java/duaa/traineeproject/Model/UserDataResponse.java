package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 10/04/18.
 */

public class UserDataResponse implements Serializable{

    int user_id;
    String user_name;
    String email;
    String phone ;
    String mobile;
    String password ;

    public UserDataResponse(int user_id, String user_name, String email, String phone, String mobile, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

 }
