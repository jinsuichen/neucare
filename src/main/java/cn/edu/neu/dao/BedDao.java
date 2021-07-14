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

    /**
     * 添加床位
     * @param bed 床位
     * @return 是否添加成功
     */
    public boolean addBed(Bed bed);

    /**
     * 更改床位的病患信息
     * @param bed 床位
     * @param pid 病患ID
     * @return 是否更改成功
     */
    public boolean updatePatient(Bed bed, Integer pid);

}
