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

    /**
     * 根据病房ID查找病房
     * @param wid 病房的ID
     * @return 病房
     */
    public Ward queryWardByWid(int wid);


   /* *//**
     * 根据床位ID查找病房
     * @param bid 床位的ID
     * @return 病房
     *//*
    public Ward queryWardByBid(int bid);*/
}
