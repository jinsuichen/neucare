package cn.edu.neu.dao;

import cn.edu.neu.pojo.Structure;
import cn.edu.neu.pojo.Ward;

import java.util.List;

public interface WardDao {

    /**
     * 根据楼层ID查找所有病房
     * @param fid 楼层的ID
     * @return 病房集合
     */
    public List<Ward> queryWardsByFid(int fid);

    /**
     * 根据病房ID查找病房
     * @param wid 病房的ID
     * @return 病房
     */
    public Ward queryWardByWid(int wid);


    /**
     * 新增病房
     * @param ward 病房
     * @return 是否新增成功
     */
    public boolean createWard(Ward ward);

}
