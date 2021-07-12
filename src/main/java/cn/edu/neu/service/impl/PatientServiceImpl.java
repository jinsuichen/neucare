package cn.edu.neu.service.impl;

import cn.edu.neu.dao.PatientDao;
import cn.edu.neu.dao.impl.PatientDaoImpl;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDao dao = new PatientDaoImpl();

    @Override
    public List<Patient> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean add(Patient patient) {
        return dao.add(patient);
    }

    @Override
    public boolean deleteByName(String name) {
        return dao.deleteByName(name);
    }

    @Override
    public boolean deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean update(Patient oldPatient, Patient newPatient) {
        return dao.update(oldPatient, newPatient);
    }
}
