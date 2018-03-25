package duaa.traineeproject.JavaObject;


import java.io.Serializable;

public class TraineeObject implements Serializable {

    String  trainer_name;
    String  email;
    String  mobile;
    String  specialization;
    String  university;
    String  phone;
    String  collage;


    public TraineeObject(String trainer_name, String email,
                         String mobile, String specialization, String university, String phone, String collage) {
        this.trainer_name = trainer_name;
        this.email = email;
        this.mobile = mobile;
        this.specialization = specialization;
        this.university = university;
        this.phone = phone;
        this.collage = collage;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUniversity() {
        return university;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
