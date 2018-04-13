package duaa.traineeproject.Model;

import java.io.Serializable;

/**
 * Created by AL-Qema on 28/03/18.
 */

public class Trainer implements Serializable {

    int id;
    String name;
    String email;


    public Trainer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
