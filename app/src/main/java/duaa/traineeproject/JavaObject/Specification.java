package duaa.traineeproject.JavaObject;

/**
 * Created by AL-Qema on 17/03/18.
 */

public class Specification {

    String name ;
    String id ;

    public Specification(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
