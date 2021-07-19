package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.WardDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Structure;
import cn.edu.neu.pojo.Ward;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

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
        for (Ward ward : DataBase.wardData) {
            if (!ward.getDeleted() && ward.getFid() == fid) {
                list.add(ward);
            }
        }
        return list;
    }

    /**
     * 根据病房ID查找病房
     *
     * @param wid 病房的ID
     * @return 病房
     */
    @Override
    public Ward queryWardByWid(int wid) {
        for (Ward ward : DataBase.wardData){
            if(!ward.getDeleted() && ward.getWid() == wid){
                return ward;
            }
        }
        return null;
    }

    /**
     * 新增病房
     * @param ward 病房
     * @return 是否新增成功
     */
    @Override
    public boolean createWard(Ward ward) {
        int maxQid = 1;
        for (Ward w : DataBase.wardData) {
            maxQid = max(maxQid, w.getWid());
        }
        ward.setWid(maxQid + 1);
        ward.setDeleted(false);
        DataBase.wardData.add(ward);
        return true;
    }


}
