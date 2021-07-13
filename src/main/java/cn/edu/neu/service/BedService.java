package cn.edu.neu.service;

import cn.edu.neu.pojo.Bed;

import java.util.List;

public interface BedService {

    /**
     * 根据房间ID获取所有床位
     * @param wid 病房ID
     * @return 床位集合
     */
    public List<Bed> getBedsByWid(int wid);

    /**
     * 将两名患者的床位对调
     * @param bed1 床位1
     * @param bed2 床位2
     * @return 是否对调成功
     */
    public Boolean swap(Bed bed1, Bed bed2);

    /**
     * 新增床位
     * @param bed 床位
     * @return 是否增加成功
     */
    public Boolean addBed(Bed bed);

    //TODO start here

}
