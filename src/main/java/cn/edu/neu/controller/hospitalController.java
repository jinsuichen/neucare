package cn.edu.neu.controller;

import cn.edu.neu.service.BedService;
import cn.edu.neu.service.EmployeeService;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.impl.BedServiceImpl;
import cn.edu.neu.service.impl.EmployeeServiceImpl;
import cn.edu.neu.service.impl.PatientServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class hospitalController {

    @FXML
    private Label patientTot;
    @FXML
    private Label bedLeft;
    @FXML
    private Label employeeTot;


    public hospitalController() {
    }

    private PatientService patientService = new PatientServiceImpl();
    private BedService bedService = new BedServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @FXML
    public void initialize(){

        patientTot.setText("病患总数:  " + patientService.getAllPatients().size());
        bedLeft.setText("床位总数:  " + bedService.getAllBeds().size());
        employeeTot.setText("在职员工:  " + employeeService.getAllEmployees().size());

    }
}
