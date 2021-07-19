package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.StructureDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Structure;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

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


    /**
     * 新增建筑
     * @param structure 建筑
     * @return 是否新增成功
     */
    @Override
    public boolean createStructure(Structure structure) {
        int maxQid = 1;
        for (Structure s : DataBase.structureData) {
            maxQid = max(maxQid, s.getSid());
        }
        structure.setSid(maxQid + 1);
        structure.setDeleted(false);
        DataBase.structureData.add(structure);
        return true;
    }


}
