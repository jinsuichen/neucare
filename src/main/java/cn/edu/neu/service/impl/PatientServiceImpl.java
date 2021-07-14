package cn.edu.neu.service.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.dao.impl.PatientDaoImpl;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDao dao = new PatientDaoImpl();

    //TODO 写注释

    @Override
    public List<Patient> getAllPatients() {
        return dao.queryAllPatients();
    }

    @Override
    public boolean addPatient(Patient patient) {
        return dao.createPatient(patient);
    }

    @Override
    public boolean deletePatientByName(String name) {
        return dao.deletePatientByName(name);
    }

    @Override
    public boolean deletePatientById(int id) {
        return dao.deletePatientById(id);
    }

    @Override
    public boolean updatePatient(Patient oldPatient, Patient newPatient) {
        return dao.updatePatient(oldPatient, newPatient);
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
}
