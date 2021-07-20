package cn.edu.neu.service.impl;

import cn.edu.neu.dao.EvaluationInfoDao;
import cn.edu.neu.dao.impl.EvaluationInfoDaoImpl;
import cn.edu.neu.pojo.EvaluationInfo;
import cn.edu.neu.service.EvaluationInfoService;

import java.util.List;

public class EvaluationInfoServiceImpl implements EvaluationInfoService {

    private final EvaluationInfoDao evaluationInfoDao = new EvaluationInfoDaoImpl();

    /**
     * 查询所有评估信息
     * @return 评估信息集合
     */
    @Override
    public List<EvaluationInfo> getAllEvaluationInfos() {
        return evaluationInfoDao.queryAllEvaluationInfos();
    }


    /**
     * 根据评估信息的ID删除评估信息
     * @param iid 评估信息的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteEvaluationInfoByIid(int iid) {
        return evaluationInfoDao.deleteEvaluationInfoByIid(iid);
    }
}
