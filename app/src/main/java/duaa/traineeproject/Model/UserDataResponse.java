package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 10/04/18.
 */

public class UserDataResponse implements Serializable{

    int id;
    String email;
    String name;
    String address ;
    String image;
    String phoneNumber ;
    String mobileNumber ;

    public UserDataResponse(int id, String email, String name,
                            String address, String image, String phoneNumber, String mobileNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
