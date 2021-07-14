package cn.edu.neu.service.impl;

import cn.edu.neu.dao.StructureDao;
import cn.edu.neu.dao.impl.StructureDaoImpl;
import cn.edu.neu.pojo.Structure;
import cn.edu.neu.service.StructureService;

import java.util.List;

public class StructureServiceImpl implements StructureService {


    private final StructureDao structureDao = new StructureDaoImpl();


    /**
     * 获取所有建筑
     * @return 建筑集合
     */
    @Override
    public List<Structure> getAllStructures() {
        return structureDao.queryAllStructures();
    }
}
