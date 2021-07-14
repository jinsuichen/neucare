package cn.edu.neu.pojo;

public class Structure {
    private Integer sid;
    private String name;
    private boolean isDeleted;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /*@Override
    public String toString() {
        return "Structure{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }*/

    @Override
    public String toString() {
        return name;
    }
}
