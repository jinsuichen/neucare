package cn.edu.neu.dao;

import cn.edu.neu.pojo.Structure;

import java.util.ArrayList;
import java.util.List;

public interface StructureDao {

    /**
     * 查询所有楼
     * @return 楼的集合
     */
    public List<Structure> queryAllStructures();

    /**
     * 根据建筑的ID查询建筑
     * @param sid 建筑ID
     * @return 建筑
     */
    public Structure queryStructureBySid(int sid);

}
