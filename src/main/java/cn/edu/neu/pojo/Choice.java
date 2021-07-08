package cn.edu.neu.pojo;

public class Choice {

    private Integer cid;
    private String description;
    private Integer score;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "cid=" + cid +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }
}
