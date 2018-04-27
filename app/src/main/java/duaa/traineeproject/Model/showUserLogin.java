package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class showUserLogin implements Serializable {

    int user_id;
    String user_name;
    String access_token;
    String email;
    String phone ;
    String mobile;
    String password ;
    int is_deleted ;
    int role_id ;

    public showUserLogin(){

    }

    public showUserLogin(int user_id, String user_name, String access_token, String email, String phone, String mobile, String password, int is_deleted, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.access_token = access_token;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.password = password;
        this.is_deleted = is_deleted;
        this.role_id = role_id;
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
