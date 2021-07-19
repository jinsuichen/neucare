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

    /**
     * 根据病房ID获得病房
     * @param wid 病房ID
     * @return 病房
     */
    public Ward getWardByWid(int wid);


    /**
     * 新增病房
     * @param ward 病房
     * @return 是否新增成功
     */
    public boolean addWard(Ward ward);

}
