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

    /**
     * 添加床位
     * @param bed 床位
     * @return 是否添加成功
     */
    @Override
    public boolean addBed(Bed bed) {
        DataBase.bedData.add(bed);
        return true;
    }

    /**
     * 更改床位的病患信息
     * @param bed 床位
     * @param pid 病患ID
     * @return 是否更改成功
     */
    @Override
    public boolean updatePatient(Bed bed, Integer pid) {
        bed.setPid(pid);
        return true;
    }
}
