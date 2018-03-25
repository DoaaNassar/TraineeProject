package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AL-Qema on 09/03/18.
 */

public class University implements Serializable{
    @SerializedName("universiy_id")
    private int universiy_id ;
    @SerializedName("universiy_name")
    private String universiy_name;
    @SerializedName("address")
    private String address ;
    @SerializedName("logo")
    private String logo ;

    public University(int universiy_id, String universiy_name, String address, String logo) {
        this.universiy_id = universiy_id;
        this.universiy_name = universiy_name;
        this.address = address;
        this.logo = logo;
    }

    public int getUniversiy_id() {
        return universiy_id;
    }

    public void setUniversiy_id(int universiy_id) {
        this.universiy_id = universiy_id;
    }

    public String getUniversiy_name() {
        return universiy_name;
    }

    public void setUniversiy_name(String universiy_name) {
        this.universiy_name = universiy_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
