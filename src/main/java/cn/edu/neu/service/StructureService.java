package cn.edu.neu.service;

import cn.edu.neu.pojo.Structure;

import java.util.List;

public interface StructureService {

    /**
     * 获取所有建筑
     * @return 建筑集合
     */
    public List<Structure> getAllStructures();

}
