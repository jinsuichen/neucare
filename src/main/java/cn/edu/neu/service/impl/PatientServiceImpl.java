package cn.edu.neu.service.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.dao.impl.PatientDaoImpl;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDao dao = new PatientDaoImpl();

    @Override
    public List<Patient> getAllPatients() {
        return dao.queryAllPatients();
    }

    @Override
    public boolean addPatient(Patient patient) {
        return dao.addPatient(patient);
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
}
