package cn.edu.neu.dao;

import cn.edu.neu.pojo.EvaluationInfo;

import java.util.List;

public interface EvaluationInfoDao {

    /**
     * 查询所有评估信息
     * @return 评估信息集合
     */
    public List<EvaluationInfo> queryAllEvaluationInfos();

    /**
     * 根据评估信息的ID删除评估信息
     * @param iid 评估信息的ID
     * @return 是否删除成功
     */
    public boolean deleteEvaluationInfoByIid(int iid);

}
