package duaa.traineeproject.JavaObject;

import java.io.Serializable;

/**
 * Created by AL-Qema on 14/04/18.
 */

public class PartObject implements Serializable{
String name;
int number ;

    public PartObject(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
