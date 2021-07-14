package cn.edu.neu.pojo;

public class Floor {
    private Integer fid;
    private Integer sid;
    private Integer height;
    private Boolean isDeleted;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /*@Override
    public String toString() {
        return "Floor{" +
                "fid=" + fid +
                ", sid=" + sid +
                ", height=" + height +
                ", isDeleted=" + isDeleted +
                '}';
    }*/
    @Override
    public String toString() {
        return height + "楼";
    }
}
