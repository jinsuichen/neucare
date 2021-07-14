package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.StructureDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Structure;

import java.util.ArrayList;
import java.util.List;

public class StructureDaoImpl implements StructureDao {

    /**
     * 查询所有楼
     *
     * @return 楼的集合
     */
    @Override
    public List<Structure> queryAllStructures() {
        List<Structure> list = new ArrayList<>();
        for(Structure structure : DataBase.structureData){
            if(!structure.isDeleted()){
                list.add(structure);
            }
        }
        return list;
    }


    /**
     * 根据建筑的ID查询建筑
     *
     * @param sid 建筑ID
     * @return 建筑
     */
    @Override
    public Structure queryStructureBySid(int sid) {
        for(Structure structure : DataBase.structureData){
            if(!structure.isDeleted() && structure.getSid() == sid){
                return structure;
            }
        }
        return null;
    }


}
