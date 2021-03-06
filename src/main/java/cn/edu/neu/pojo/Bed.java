package cn.edu.neu.pojo;

public class Bed {

    private Integer bid;
    private Integer pid;
    private Integer wid;
    private String name;
    private boolean isDeleted;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
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

    @Override
    public String toString() {
        return name;
    }

    /*@Override
    public String toString() {
        return "Bed{" +
                "bid=" + bid +
                ", pid=" + pid +
                ", wid=" + wid +
                ", name='" + name + '\'' +
                ", isDelete=" + isDeleted +
                '}';
    }*/
}
