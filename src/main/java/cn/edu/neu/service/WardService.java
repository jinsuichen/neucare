package cn.edu.neu.service;

import cn.edu.neu.pojo.Ward;

import java.util.List;

public interface WardService {

    /**
     * 根据楼层ID获取病房
     * @param fid 楼层的ID
     * @return 病房集合
     */
    public List<Ward> getWardsByFid(int fid);

}
