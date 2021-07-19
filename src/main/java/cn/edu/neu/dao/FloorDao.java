package cn.edu.neu.dao;

import cn.edu.neu.pojo.Floor;
import cn.edu.neu.pojo.Structure;

import java.util.List;

public interface FloorDao {

    /**
     * 根据楼的ID查找楼内的所有楼层
     * @param sid 楼的ID
     * @return 楼层集合
     */
    public List<Floor> queryFloorsBySid(int sid);

    /**
     * 根据楼层的ID查找楼层
     * @param fid 楼层的ID
     * @return 楼层
     */
    public Floor queryFloorByFid(int fid);


    /**
     * 新增楼层
     * @param floor 楼层
     * @return 是否新增成功
     */
    public boolean createFloor(Floor floor);


}
