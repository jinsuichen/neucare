package cn.edu.neu.pojo;

public class Patient {
    private Integer pid;
    private String name;
    private Integer age;
    private String gender;
    private String telephone;
    private String emergencyContact;
    private String emergencyTelephone;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyTelephone() {
        return emergencyTelephone;
    }

    public void setEmergencyTelephone(String emergencyTelephone) {
        this.emergencyTelephone = emergencyTelephone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pid=" + pid +
                ", pname='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyTelephone='" + emergencyTelephone + '\'' +
                '}';
    }
}
