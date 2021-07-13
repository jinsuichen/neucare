package cn.edu.neu.dao;

import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface PatientDao {


    /**
     * 根据姓名查找病患信息
     * @param name 病患姓名
     * @return 病患。当未找到时返回null
     */
    public Patient queryPatientByName(String name);

    /**
     * 查询所有病患信息并返回
     * @return 病患列表
     */
    public List<Patient> queryAllPatients();

    /**
     * 增加病患
     * @param patient 病患
     * @return 是否增加成功
     */
    public boolean addPatient(Patient patient);


    /**
     * 根据病患ID删除病患
     * @param id 病患ID
     * @return 是否删除成功
     */
    public boolean deletePatientById(int id);

    /**
     * 根据病患姓名删除病患
     * @param name 病患姓名
     * @return 是否删除成功
     */
    public boolean deletePatientByName(String name);

    /**
     * 更改病患信息
     * @param oldPatient 原有病患
     * @param newPatient 更新后的病患
     * @return 是否更新成功
     */
    public boolean updatePatient(Patient oldPatient, Patient newPatient);

}
