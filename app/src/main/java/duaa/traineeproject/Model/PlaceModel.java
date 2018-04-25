package duaa.traineeproject.Model;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class PlaceModel {
    int place_id ;
    String place_name;
    String email;
    String phone ;
    String mobile ;
    String address ;

    public PlaceModel(int place_id, String place_name, String email, String phone, String mobile, String address) {
        this.place_id = place_id;
        this.place_name = place_name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.address = address;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
