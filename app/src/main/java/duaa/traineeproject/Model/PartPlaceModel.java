package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class PartPlaceModel implements Serializable {
    int place_partment_id;
    int place_id;
    int hours;
    String partment_name;

    public PartPlaceModel(int place_partment_id, int place_id, int hours, String partment_name) {
        this.place_partment_id = place_partment_id;
        this.place_id = place_id;
        this.hours = hours;
        this.partment_name = partment_name;
    }

    public int getPlace_partment_id() {
        return place_partment_id;
    }

    public void setPlace_partment_id(int place_partment_id) {
        this.place_partment_id = place_partment_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getPartment_name() {
        return partment_name;
    }

    public void setPartment_name(String partment_name) {
        this.partment_name = partment_name;
    }
}
