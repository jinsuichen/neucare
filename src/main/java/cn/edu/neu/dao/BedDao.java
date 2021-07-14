package cn.edu.neu.dao;

import cn.edu.neu.pojo.Bed;

import java.util.List;

public interface BedDao {


    /**
     * 查询所有床位
     * @return 床位集合
     */
    public List<Bed> queryAllBeds();

    /**
     * 根据建筑ID查找所有床位
     * @param sid 建筑的ID
     * @return 床位集合
     */
    public List<Bed> queryBedsBySid(int sid);

    /**
     * 根据楼层ID查找所有床位
     * @param fid 楼层的ID
     * @return 床位集合
     */
    public List<Bed> queryBedsByFid(int fid);

    /**
     * 根据病房ID查找所有床位
     * @param wid 病房的ID
     * @return 床位集合
     */
    public List<Bed> queryBedsByWid(int wid);

    /**
     * 根据床位ID获得床位
     * @param bid 床位ID
     * @return 床位
     */
    public Bed queryBedByBid(int bid);

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
