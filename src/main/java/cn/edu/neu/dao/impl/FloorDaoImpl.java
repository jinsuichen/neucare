package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.FloorDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorDaoImpl implements FloorDao {

    /**
     * 根据楼的ID查找楼内的所有楼层
     * @param sid 楼的ID
     * @return 楼层集合
     */
    @Override
    public List<Floor> queryFloorsBySid(int sid) {
        List<Floor> list = new ArrayList<>();

        for( Floor floor : DataBase.floorData){
            if(!floor.getDeleted() && floor.getSid() == sid){
                list.add(floor);
            }
        }
        return list;
    }

    /**
     * 根据楼层的ID查找楼层
     * @param fid 楼层的ID
     * @return 楼层
     */
    @Override
    public Floor queryFloorByFid(int fid) {
        for(Floor floor : DataBase.floorData){
            if(!floor.getDeleted() && floor.getFid() == fid){
                return floor;
            }
        }
        return null;
    }
}
