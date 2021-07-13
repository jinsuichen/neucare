package cn.edu.neu.dao;

import cn.edu.neu.pojo.Ward;

import java.util.List;

public interface WardDao {

    /**
     * 根据楼层ID查找所有病房
     * @param fid 楼层的ID
     * @return 病房集合
     */
    public List<Ward> queryWardsByFid(int fid);
}
