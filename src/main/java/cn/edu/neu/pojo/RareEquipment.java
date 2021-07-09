package cn.edu.neu.pojo;

public class RareEquipment {

    private Integer rid;
    private RareEquipmentType type;
    private Integer fid;
    private boolean isDeleted;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public RareEquipmentType getType() {
        return type;
    }

    public void setType(RareEquipmentType type) {
        this.type = type;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "RareEquipment{" +
                "rid=" + rid +
                ", type=" + type +
                ", fid=" + fid +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
