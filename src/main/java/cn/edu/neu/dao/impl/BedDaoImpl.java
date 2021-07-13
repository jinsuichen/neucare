package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.BedDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Bed;

import java.util.ArrayList;
import java.util.List;

public class BedDaoImpl implements BedDao {

    /**
     * 根据病房ID查找所有床位
     *
     * @param wid 病房的ID
     * @return 床位集合
     */
    @Override
    public List<Bed> queryBedsByWid(int wid) {
        List<Bed> list = new ArrayList<>();
        for(Bed bed : DataBase.bedData){
            if(bed.getWid() == wid){
                list.add(bed);
            }
        }
        return list;
    }
}
