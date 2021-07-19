package cn.edu.neu.dao;

import cn.edu.neu.pojo.Structure;
import com.sun.corba.se.impl.presentation.rmi.StubConnectImpl;

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


    /**
     * 新增建筑
     * @param structure 建筑
     * @return 是否新增成功
     */
    public boolean createStructure(Structure structure);

}
