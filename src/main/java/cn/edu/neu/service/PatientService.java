package cn.edu.neu.service;

import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface PatientService {

    /**
     * 查询所有病患信息
     *
     * @return 病患列表
     */
    public List<Patient> getAllPatients();


    /**
     * 根据病患ID查找病患
     * @param pid 病患ID
     * @return 病患
     */
    public Patient getPatientByPid(int pid);


    /**
     * 获取所有没有床位的病患信息
     * @return 病患集合
     */
    public List<Patient> getAllPatientsWithNoBed();

    /**
     * 增加病患
     *
     * @param patient 待添加的病患
     * @return 是否增加成功
     */
    public boolean addPatient(Patient patient);

    /**
     * 根据姓名删除病患
     *
     * @param name 患者姓名
     * @return 是否删除成功
     */
    public boolean deletePatientByName(String name);


    /**
     * 根据ID删除病患
     *
     * @param id 病患ID
     * @return 是否删除成功
     */
    public boolean deletePatientById(int id);


    /**
     * 更新病患信息
     *
     * @param oldPatient 旧病患
     * @param newPatient 新病患
     * @return 是否更新成功
     */
    public boolean updatePatient(Patient oldPatient, Patient newPatient);


    /**
     *
     * @param pid 病患ID
     * @param newPatient 更改后的病患对象
     * @return 是否更新成功
     */
    public boolean updatePatientByPid(int pid, Patient newPatient);

    /**
     * 根据床位的ID获取病患信息
     * @param bid 床位的ID
     * @return 病患
     */
    public Patient getPatientByBid(int bid);

    /**
     * 根据病患的姓名模糊查询病患
     * @param keyword 关键词
     * @return 病患集合
     */
    public List<Patient> fuzzyQueryPatients(String keyword);



}
