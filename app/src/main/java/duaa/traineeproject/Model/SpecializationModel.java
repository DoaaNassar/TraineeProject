package duaa.traineeproject.Model;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class SpecializationModel {
   int   specalization_id;
   int  collage_id ;
   String specalization_name ;

    public SpecializationModel(int specalization_id, int collage_id, String specalization_name) {
        this.specalization_id = specalization_id;
        this.collage_id = collage_id;
        this.specalization_name = specalization_name;
    }

    public int getSpecalization_id() {
        return specalization_id;
    }

    public void setSpecalization_id(int specalization_id) {
        this.specalization_id = specalization_id;
    }

    public int getCollage_id() {
        return collage_id;
    }

    public void setCollage_id(int collage_id) {
        this.collage_id = collage_id;
    }

    public String getSpecalization_name() {
        return specalization_name;
    }

    public void setSpecalization_name(String specalization_name) {
        this.specalization_name = specalization_name;
    }
}
