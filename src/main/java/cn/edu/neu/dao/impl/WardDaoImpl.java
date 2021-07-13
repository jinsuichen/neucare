package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.WardDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Ward;

import java.util.ArrayList;
import java.util.List;

public class WardDaoImpl implements WardDao {

    /**
     * 根据楼层ID查找所有病房
     *
     * @param fid 楼层的ID
     * @return 病房集合
     */
    @Override
    public List<Ward> queryWardsByFid(int fid) {
        List<Ward> list = new ArrayList<>();
        for(Ward ward : DataBase.wardData){
            if(ward.getFid() == fid){
                list.add(ward);
            }
        }
        return list;
    }
}