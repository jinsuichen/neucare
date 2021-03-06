package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.BedDao;
import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Bed;
import cn.edu.neu.pojo.Patient;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
     * 查询所有没有床位的病患
     *
     * @return 病患集合
     */
    @Override
    public List<Patient> queryAllPatientsWithNoBed() {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : DataBase.patientData) {
            if (!patient.isDeleted()) {
                Boolean flag = false;
                for (Bed bed : DataBase.bedData) {
                    if (!bed.isDeleted() && bed.getPid() != null && bed.getPid().equals(patient.getPid())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    list.add(patient);
                }
            }
        }
        return list;

    }

    /**
     * 根据病患ID查找病患
     *
     * @param pid 病患ID
     * @return 病患
     */
    @Override
    public Patient queryPatientByPid(int pid) {
        for (Patient patient : DataBase.patientData) {
            if (patient.getPid() == pid) {
                return patient;
            }
        }
        return null;
    }

    /**
     * 根据床位的ID查找病患
     *
     * @return 病患
     */
    @Override
    public Patient queryPatientByBid(int bid) {
        for (Bed bed : DataBase.bedData) {
            if (!bed.isDeleted() && bed.getBid() == bid) {
                int pid = bed.getPid();
                return queryPatientByPid(pid);
            }
        }
        return null;
    }

    /**
     * 根据病患的姓名模糊查询病患
     *
     * @return 病患集合
     */
    @Override
    public List<Patient> fuzzyQueryPatients(String keyword) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : DataBase.patientData) {
            if (!patient.isDeleted() && patient.getName().contains(keyword)) {
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
    public boolean createPatient(Patient patient) {
        int maxId = 0;
        for (Patient p : DataBase.patientData) {
            maxId = max(maxId, p.getPid());
        }
        patient.setPid(maxId + 1);
        patient.setDeleted(false);
        DataBase.patientData.add(patient);
        return true;

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

                for (Bed b : DataBase.bedData) {
                    if (!b.isDeleted() && b.getPid() != null && b.getPid() == id) {
                        b.setPid(null);
                        break;
                    }
                }
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
            for (Bed b : DataBase.bedData) {
                if (!b.isDeleted() && b.getPid().equals(patient.getPid())) {
                    b.setPid(null);
                    break;
                }
            }
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
        Patient patient = queryPatientByPid(oldPatient.getPid());
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
