package duaa.traineeproject.JavaObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class Place implements Serializable {

    String name ;
    List<PartObject>part ;
    String email ;
    String mobile ;
    String phone;
    String full_address ;

    public Place(String name, List<PartObject> part, String email, String mobile, String phone, String full_address) {
        this.name = name;
        this.part = part;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.full_address = full_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PartObject> getPart() {
        return part;
    }

    public void setPart(List<PartObject> part) {
        this.part = part;
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
