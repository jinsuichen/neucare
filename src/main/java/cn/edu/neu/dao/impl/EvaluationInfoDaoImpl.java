package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.EvaluationInfoDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.EvaluationInfo;

import java.util.ArrayList;
import java.util.List;

public class EvaluationInfoDaoImpl implements EvaluationInfoDao {

    /**
     * 查询所有评估信息
     * @return 评估信息集合
     */
    @Override
    public List<EvaluationInfo> queryAllEvaluationInfos() {
        List<EvaluationInfo> list = new ArrayList<>();
        for(EvaluationInfo e : DataBase.evaluationInfoData){
            if(!e.isDeleted()){
                list.add(e);
            }
        }
        return list;
    }


    /**
     * 根据评估信息的ID删除评估信息
     * @param iid 评估信息的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteEvaluationInfoByIid(int iid) {
        for(EvaluationInfo e : DataBase.evaluationInfoData){
            if(!e.isDeleted() && e.getIid() == iid){
                e.setDeleted(true);
                return true;
            }
        }
        return false;
    }


}
