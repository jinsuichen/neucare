package cn.edu.neu.dao;

import cn.edu.neu.pojo.Bed;

import java.util.List;

public interface BedDao {

    /**
     * 根据病房ID查找所有床位
     * @param wid 病房的ID
     * @return 床位集合
     */
    public List<Bed> queryBedsByWid(int wid);

}
