package cn.edu.neu.pojo;

public class Structure {
    private Integer sid;
    private String name;

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

    @Override
    public String toString() {
        return "Structure{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                '}';
    }
}
