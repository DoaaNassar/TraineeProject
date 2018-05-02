package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 02/05/18.
 */

public class NotificationModel implements Serializable {
String name ;
String date ;

    public NotificationModel(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
