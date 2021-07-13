package cn.edu.neu.dao;

import cn.edu.neu.pojo.Floor;

import java.util.List;

public interface FloorDao {

    /**
     * 根据楼的ID查找楼内的所有楼层
     * @param sid 楼的ID
     * @return 楼层集合
     */
    public List<Floor> queryFloorsBySid(int sid);
}
