package cn.edu.neu.service;

import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface PatientService {

    /**
     * 查询所有病患信息
     * @return 病患列表
     */
    public List<Patient> findAll();

    /**
     * 增加病患
     * @return 是否增加成功
     */
    public boolean add(Patient patient);

    /**
     * 根据姓名删除病患
     * @return
     */
    public boolean deleteByName(String name);


    /**
     * 根据ID删除病患
     * @param id
     * @return
     */
    public boolean deleteById(int id);


    /**
     * 更新病患信息
     * @param oldPatient 旧病患
     * @param newPatient 新病患
     * @return 是否更新成功
     */
    public boolean update(Patient oldPatient, Patient newPatient);

}
