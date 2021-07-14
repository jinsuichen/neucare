package cn.edu.neu.service;

import cn.edu.neu.pojo.Bed;
import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface BedService {


    /**
     * 获取全部床位
     * @return 床位集合
     */
    public List<Bed> getAllBeds();

    /**
     * 根据房间ID获取所有床位
     * @param wid 病房ID
     * @return 床位集合
     */
    public List<Bed> getBedsByWid(int wid);


    /**
     * 根据楼层ID获取所有床位
     * @param fid 楼层ID
     * @return 床位集合
     */
    public List<Bed> getBedsByFid(int fid);

    /**
     * 根据建筑ID获取所有床位
     *
     * @param sid 建筑ID
     * @return 床位集合
     */
    public List<Bed> getBedsBySid(int sid);


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

    /**
     * 将床位上的病患进行退院操作
     * @param bed 床位
     * @return 是否退院成功
     */
    public Boolean checkOut(Bed bed);

    /**
     * 根据床位ID获得床位
     * @param bid 床位ID
     * @return 床位
     */
    public Bed getBedByBid(int bid);

    /**
     * 病患入住
     * @param bid 床位ID
     * @param pid 病患ID
     * @return 是否入住成功
     */
    public boolean checkin(int bid, int pid);

    /**
     * 病患入住
     * @param bed 床位
     * @param patient 病患
     * @return 是否入住成功
     */
    public boolean checkin(Bed bed, Patient patient);

}
