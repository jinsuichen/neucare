package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {


    /**
     * 根据姓名查找病患信息
     * @param name 病患姓名
     * @return 病患。当未找到时返回null
     */
    @Override
    public Patient findByName(String name) {
        for(Patient patient : DataBase.patientData){
            if(name.equals(patient.getName()) && !patient.isDeleted()){
                return patient;
            }
        }
        return null;
    }

    /**
     * 查询所有病患信息并返回
     * @return 病患列表
     */
    @Override
    public List<Patient> findAll() {
        List<Patient> list = new ArrayList<>();
        for(Patient patient : DataBase.patientData){
            if(!patient.isDeleted()){
                list.add(patient);
            }
        }
        return list;
    }

    /**
     * 增加病患
     * @param patient 病患
     * @return 是否增加成功
     */
    @Override
    public boolean add(Patient patient) {
        if(findByName(patient.getName()) == null){
            DataBase.patientData.add(patient);
            return true;
        }else{
            System.err.println("已有同名病患，无法正确添加");
            return false;
        }

    }

    /**
     * 根据病患姓名删除病患
     * @param name 病患姓名
     * @return 是否删除成功
     */
    @Override
    public boolean deleteByName(String name) {
        Patient patient = findByName(name);
        if(patient == null){
            System.err.println("未找到相应患者，无法正确删除。");
            return false;
        }else{
            patient.setDeleted(true);
            return true;
        }
    }

    /**
     * 更改病患信息
     * @param oldPatient 原有病患
     * @param newPatient 更新后的病患
     * @return 是否更新成功
     */
    @Override
    public boolean update(Patient oldPatient, Patient newPatient) {
        Patient patient = findByName(oldPatient.getName());
        if(patient == null){
            System.err.println("未找到相应患者，无法正确更新。");
            return false;
        }else{
            DataBase.patientData.remove(oldPatient);
            DataBase.patientData.add(newPatient);
            return true;
        }
    }
}
