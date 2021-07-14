package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.StructureDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Structure;

import java.util.List;

public class StructureDaoImpl implements StructureDao {

    /**
     * 查询所有楼
     *
     * @return 楼的集合
     */
    @Override
    public List<Structure> queryAllStructures() {
        return DataBase.structureData;
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
            if(structure.getSid() == sid){
                return structure;
            }
        }
        return null;
    }


}
