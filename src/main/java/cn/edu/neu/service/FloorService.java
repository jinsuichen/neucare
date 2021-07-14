package cn.edu.neu.service;

import cn.edu.neu.pojo.Floor;

import java.util.List;

public interface FloorService {

    /**
     * 根据建筑ID获取楼层
     * @param sid 建筑的ID
     * @return 楼层集合
     */
    public List<Floor> getFloorsBySid(int sid);

    /**
     * 根据楼层ID获得楼层
     * @param fid 楼层ID
     * @return 楼层
     */
    public Floor getFloorByFid(int fid);

}
