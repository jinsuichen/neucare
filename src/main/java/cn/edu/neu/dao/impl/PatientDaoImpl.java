package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Patient;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class PatientDaoImpl implements PatientDao {


    /**
     * 根据姓名查找病患信息
     *
     * @param name 病患姓名
     * @return 病患。当未找到时返回null
     */
    @Override
    public Patient queryPatientByName(String name) {
        for (Patient patient : DataBase.patientData) {
            if (name.equals(patient.getName()) && !patient.isDeleted()) {
                return patient;
            }
        }
        return null;
    }

    /**
     * 查询所有病患信息并返回
     *
     * @return 病患列表
     */
    @Override
    public List<Patient> queryAllPatients() {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : DataBase.patientData) {
            if (!patient.isDeleted()) {
                list.add(patient);
            }
        }
        return list;
    }

    /**
     * 增加病患
     *
     * @param patient 病患
     * @return 是否增加成功
     */
    @Override
    public boolean addPatient(Patient patient) {
        if (patient.getName() != null && queryPatientByName(patient.getName()) == null) {
            int maxId = 0;
            for (Patient p : DataBase.patientData) {
                maxId = max(maxId, p.getPid());
            }
            patient.setPid(maxId + 1);
            DataBase.patientData.add(patient);
            return true;
        } else {
            System.err.println("已有同名病患，无法正确添加");
            return false;
        }

    }


    /**
     * 根据病患ID删除病患
     *
     * @param id 病患ID
     * @return 是否删除成功
     */
    @Override
    public boolean deletePatientById(int id) {
        for (Patient p : DataBase.patientData) {
            if (p.getPid() == id && !p.isDeleted()) {
                p.setDeleted(true);
                return true;
            } else if (p.getPid() == id && p.isDeleted()) {
                System.err.println("ID为" + id + "的病患曾被删除，无需再次删除");
                return false;
            }
        }
        System.err.println("未找到ID为" + id + "的病患。");
        return false;
    }


    /**
     * 根据病患姓名删除病患
     *
     * @param name 病患姓名
     * @return 是否删除成功
     */
    @Override
    public boolean deletePatientByName(String name) {
        Patient patient = queryPatientByName(name);
        if (patient == null) {
            System.err.println("未找到相应患者，无法正确删除。");
            return false;
        } else {
            patient.setDeleted(true);
            return true;
        }
    }

    /**
     * 更改病患信息
     *
     * @param oldPatient 原有病患
     * @param newPatient 更新后的病患
     * @return 是否更新成功
     */
    @Override
    public boolean updatePatient(Patient oldPatient, Patient newPatient) {
        Patient patient = queryPatientByName(oldPatient.getName());
        if (patient == null) {
            System.err.println("未找到相应患者，无法正确更新。");
            return false;
        } else {
            DataBase.patientData.remove(oldPatient);
            DataBase.patientData.add(newPatient);
            return true;
        }
    }
}
