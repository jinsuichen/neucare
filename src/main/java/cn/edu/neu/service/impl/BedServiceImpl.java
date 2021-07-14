package cn.edu.neu.service.impl;

import cn.edu.neu.dao.BedDao;
import cn.edu.neu.dao.impl.BedDaoImpl;
import cn.edu.neu.pojo.Bed;
import cn.edu.neu.service.BedService;

import java.util.List;

public class BedServiceImpl implements BedService {


    private final BedDao bedDao = new BedDaoImpl();

    /**
     * 根据房间ID获取所有床位
     * @param wid 病房ID
     * @return 床位集合
     */
    @Override
    public List<Bed> getBedsByWid(int wid) {
        return bedDao.queryBedsByWid(wid);
    }

    /**
     * 将两名患者的床位对调
     * @param bed1 床位1
     * @param bed2 床位2
     * @return 是否对调成功
     */
    @Override
    public Boolean swap(Bed bed1, Bed bed2) {
        if(bed1 == null || bed2 == null || bed1.getPid() == null && bed2.getPid() == null){
            return false;
        }
        int pid1 = bed1.getPid();
        int pid2 = bed2.getPid();
        Boolean flag1 = bedDao.updatePatient(bed1, pid2);
        Boolean flag2 = bedDao.updatePatient(bed2, pid1);

        return flag1 && flag2;
    }

    /**
     * 新增床位
     * @param bed 床位
     * @return 是否增加成功
     */
    @Override
    public Boolean addBed(Bed bed) {
        return bedDao.addBed(bed);
    }

    /**
     * 将床位上的病患进行退院操作
     * @param bed 床位
     * @return 是否退院成功
     */
    @Override
    public Boolean checkOut(Bed bed) {
        bedDao.updatePatient(bed, null);
        return true;
    }
}
