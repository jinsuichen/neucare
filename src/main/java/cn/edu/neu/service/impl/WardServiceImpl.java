package cn.edu.neu.service.impl;

import cn.edu.neu.dao.WardDao;
import cn.edu.neu.dao.impl.WardDaoImpl;
import cn.edu.neu.pojo.Ward;
import cn.edu.neu.service.WardService;

import java.util.List;

public class WardServiceImpl implements WardService {

    private final WardDao wardDao = new WardDaoImpl();


    /**
     * 根据楼层ID获取病房
     * @param fid 楼层的ID
     * @return 病房集合
     */
    @Override
    public List<Ward> getWardsByFid(int fid) {
        return wardDao.queryWardsByFid(fid);
    }
}
