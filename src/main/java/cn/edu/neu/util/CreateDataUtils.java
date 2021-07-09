package cn.edu.neu.util;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;


//fixme 删除这个类

/**
 * 该类提供将创建数据并写入json文件的方法
 */
public class CreateDataUtils {

    public static void main(String[] args) {
        createEmployee();
        createPatient();
    }

    public static void createPatient() {

        ArrayList<Patient> list = new ArrayList<>();


        for(int i = 1; i<=100; i++){
            Patient patient = new Patient();

            patient.setPid(i);
            patient.setAge(18 + i);
            patient.setName("cjs");
            patient.setTelephone("13998337244");
            patient.setGender("男");
            patient.setEmergencyContact("sxw");
            patient.setEmergencyTelephone("120");
            patient.setDeleted(false);

            list.add(patient);
        }

        try {
            JsonUtils.writeDateToStream(list, new FileOutputStream(new File("src/main/resources/data/patient.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void createEmployee() {

        ArrayList<Employee> list = new ArrayList<>();


        for(int i = 1; i<=100; i++){
            Employee employee = new Employee();

            employee.setEid(i);
            employee.setBirthday("2002-06-07");
            employee.setName("doc");
            employee.setExpertSkill("play");
            employee.setIdentificationNumber("210106200206073619");
            employee.setTelephone("13998337244");
            employee.setUsername("admin"+i);
            employee.setPassword("admin");
            employee.setDeleted(false);

            int t = i % 3;
            if(t ==0)
                employee.setType(EmployeeType.DOCTOR);
            else if(t ==1)
                employee.setType(EmployeeType.NURSE);
            else
                employee.setType(EmployeeType.CAREWORKER);


            list.add(employee);
        }

        try {
            JsonUtils.writeDateToStream(list, new FileOutputStream(new File("src/main/resources/data/employee.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
