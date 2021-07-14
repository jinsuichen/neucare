package cn.edu.neu.service.impl;

import cn.edu.neu.dao.FloorDao;
import cn.edu.neu.dao.impl.FloorDaoImpl;
import cn.edu.neu.pojo.Floor;
import cn.edu.neu.service.FloorService;

import java.util.List;

public class FloorServiceImpl implements FloorService {

    private final FloorDao floorDao = new FloorDaoImpl();

    /**
     * 根据建筑ID获取楼层
     * @param sid 建筑的ID
     * @return 楼层集合
     */
    @Override
    public List<Floor> getFloorsBySid(int sid) {
        return floorDao.queryFloorsBySid(sid);
    }
}
