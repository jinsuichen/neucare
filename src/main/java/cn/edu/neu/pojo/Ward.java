package cn.edu.neu.pojo;

public class Ward {
    private Integer wid;
    private Integer fid;
    private String name;
    private Boolean isDeleted;

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /*@Override
    public String toString() {
        return "Ward{" +
                "wid=" + wid +
                ", fid=" + fid +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }*/

    @Override
    public String toString() {
        return name;
    }
}
