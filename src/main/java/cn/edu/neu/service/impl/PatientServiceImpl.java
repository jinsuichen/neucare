package cn.edu.neu.service.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.dao.impl.PatientDaoImpl;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDao dao = new PatientDaoImpl();


    /**
     * 查询所有病患信息
     *
     * @return 病患列表
     */
    @Override
    public List<Patient> getAllPatients() {
        return dao.queryAllPatients();
    }


    /**
     * 根据病患ID查找病患
     * @param pid 病患ID
     * @return 病患
     */
    @Override
    public Patient getPatientByPid(int pid) {
        return dao.queryPatientByPid(pid);
    }

    /**
     * 获取所有没有床位的病患信息
     * @return 病患集合
     */
    @Override
    public List<Patient> getAllPatientsWithNoBed() {
        return dao.queryAllPatientsWithNoBed();
    }

    /**
     * 增加病患
     *
     * @param patient 待添加的病患
     * @return 是否增加成功
     */
    @Override
    public boolean addPatient(Patient patient) {
        return dao.createPatient(patient);
    }

    /**
     * 根据姓名删除病患
     *
     * @param name 患者姓名
     * @return 是否删除成功
     */
    @Override
    public boolean deletePatientByName(String name) {
        return dao.deletePatientByName(name);
    }

    /**
     * 根据ID删除病患
     *
     * @param id 病患ID
     * @return 是否删除成功
     */
    @Override
    public boolean deletePatientById(int id) {
        return dao.deletePatientById(id);
    }

    /**
     * 更新病患信息
     *
     * @param oldPatient 旧病患
     * @param newPatient 新病患
     * @return 是否更新成功
     */
    @Override
    public boolean updatePatient(Patient oldPatient, Patient newPatient) {
        return dao.updatePatient(oldPatient, newPatient);
    }

    /**
     *
     * @param pid 病患ID
     * @param newPatient 更改后的病患对象
     * @return 是否更新成功
     */
    @Override
    public boolean updatePatientByPid(int pid, Patient newPatient) {
        Patient patient = dao.queryPatientByPid(pid);
        if(patient == null){
            return false;
        }
        patient = newPatient;
        return true;
    }

    /**
     * 根据床位的ID获取病患信息
     * @param bid 床位的ID
     * @return 病患
     */
    @Override
    public Patient getPatientByBid(int bid) {
        return dao.queryPatientByBid(bid);
    }

    /**
     * 根据病患的姓名模糊查询病患
     * @param keyword 关键词
     * @return 病患集合
     */
    @Override
    public List<Patient> fuzzyQueryPatients(String keyword) {
        return dao.fuzzyQueryPatients(keyword);
    }
}
