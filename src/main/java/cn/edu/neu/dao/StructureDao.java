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

}
