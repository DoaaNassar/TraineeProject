package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 02/05/18.
 */

public class NotificationModel implements Serializable {
    int id;
    String name;
    String date;

    public NotificationModel(int id, String name, String date) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
