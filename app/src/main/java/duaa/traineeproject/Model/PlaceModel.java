package duaa.traineeproject.Model;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class PlaceModel {
    String name;
    String number ;

    public PlaceModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
