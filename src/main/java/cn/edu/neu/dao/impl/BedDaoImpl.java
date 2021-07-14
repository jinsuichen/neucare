package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.BedDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Bed;
import cn.edu.neu.pojo.Floor;
import cn.edu.neu.pojo.Ward;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;

public class BedDaoImpl implements BedDao {


    /**
     * 查询所有床位
     * @return 床位集合
     */
    @Override
    public List<Bed> queryAllBeds() {
        List<Bed> list = new ArrayList<>();
        for(Bed bed : DataBase.bedData){
            if(!bed.isDeleted()){
                list.add(bed);
            }
        }
        return list;
    }

    /**
     * 根据建筑ID查找所有床位
     *
     * @param sid 建筑的ID
     * @return 床位集合
     */
    @Override
    public List<Bed> queryBedsBySid(int sid) {
        List<Bed> list = new ArrayList<>();
        for(Floor floor : DataBase.floorData){
            if(!floor.getDeleted() && floor.getSid() == sid){
                list.addAll(queryBedsByFid(floor.getFid()));
            }
        }
        return list;
    }

    /**
     * 根据楼层ID查找所有床位
     *
     * @param fid 楼层的ID
     * @return 床位集合
     */
    @Override
    public List<Bed> queryBedsByFid(int fid) {
        List<Bed> list = new ArrayList<>();
        for (Ward ward : DataBase.wardData) {
            if (!ward.getDeleted() && ward.getFid() == fid) {
                list.addAll(queryBedsByWid(ward.getWid()));
            }
        }
        return list;
    }

    /**
     * 根据病房ID查找所有床位
     *
     * @param wid 病房的ID
     * @return 床位集合
     */
    @Override
    public List<Bed> queryBedsByWid(int wid) {
        List<Bed> list = new ArrayList<>();
        for (Bed bed : DataBase.bedData) {
            if (!bed.isDeleted() && bed.getWid() == wid) {
                list.add(bed);
            }
        }
        return list;
    }

    /**
     * 根据床位ID获得床位
     *
     * @param bid 床位ID
     * @return 床位
     */
    @Override
    public Bed queryBedByBid(int bid) {
        for (Bed bed : DataBase.bedData) {
            if (!bed.isDeleted() && bed.getBid() == bid) {
                return bed;
            }
        }
        return null;
    }

    /**
     * 添加床位
     *
     * @param bed 床位
     * @return 是否添加成功
     */
    @Override
    public boolean addBed(Bed bed) {
        int maxId = 0;
        for (Bed b : DataBase.bedData) {
            maxId = max(maxId, b.getBid());
        }
        bed.setBid(maxId + 1);
        DataBase.bedData.add(bed);
        return true;
    }

    /**
     * 更改床位的病患信息
     *
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
