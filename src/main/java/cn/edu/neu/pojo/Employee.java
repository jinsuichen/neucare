package cn.edu.neu.pojo;

import java.util.Objects;

public class Employee implements Comparable<Employee>{
    private Integer eid;
    private String username;
    private String password;
    private String name;
    private EmployeeType type;
    private String birthday;
    private String expertSkill;
    private String telephone;
    private String identificationNumber;
    private boolean isDeleted;

    public Employee() {

    }

    public Employee(String username) {
        this.username = username;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getExpertSkill() {
        return expertSkill;
    }

    public void setExpertSkill(String expertSkill) {
        this.expertSkill = expertSkill;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", birthday='" + birthday + '\'' +
                ", expertSkill='" + expertSkill + '\'' +
                ", telephone='" + telephone + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", isDelete=" + isDeleted +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.username.compareTo(o.username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isDeleted == employee.isDeleted && Objects.equals(username, employee.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, isDeleted);
    }
}
