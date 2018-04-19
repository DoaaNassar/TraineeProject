package duaa.traineeproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import duaa.traineeproject.JavaObject.Specification;

/**
 * Created by AL-Qema on 12/04/18.
 */

public class Faculty implements Serializable {
    private int collage_id ;
    private String collage_name;

    public Faculty(int collage_id, String collage_name) {
        this.collage_id = collage_id;
        this.collage_name = collage_name;
    }

    public int getCollage_id() {
        return collage_id;
    }

    public void setCollage_id(int collage_id) {
        this.collage_id = collage_id;
    }

    public String getCollage_name() {
        return collage_name;
    }

    public void setCollage_name(String collage_name) {
        this.collage_name = collage_name;
    }
}
