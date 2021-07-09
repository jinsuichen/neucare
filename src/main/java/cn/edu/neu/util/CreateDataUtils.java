package cn.edu.neu.util;

import cn.edu.neu.pojo.Admin;
import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;

import java.io.IOException;
import java.util.ArrayList;


//fixme 删除这个类

/**
 * 该类提供将创建数据并写入json文件的方法
 */
public class CreateDataUtils {

    public static void main(String[] args) {

        createAdmin();
        createEmployee();
        createPatient();
    }

    public static void createAdmin(){
        ArrayList<Admin> list = new ArrayList<>();
        for(int i = 1; i<=3; i++){
            Admin admin = new Admin();
            admin.setUsername("admin"+i);
            admin.setPassword("admin");
            list.add(admin);
        }
        System.out.println(JsonUtils.serialize(list));

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/admin.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void createPatient() {

        ArrayList<Patient> list = new ArrayList<>();


        for (int i = 1; i <= 100; i++) {
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
            FileUtils.write(JsonUtils.serialize(list), "data/patient.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void createEmployee() {

        ArrayList<Employee> list = new ArrayList<>();


        for (int i = 1; i <= 100; i++) {
            Employee employee = new Employee();

            employee.setEid(i);
            employee.setBirthday("2002-06-07");
            employee.setName("doc");
            employee.setExpertSkill("play");
            employee.setIdentificationNumber("210106200206073619");
            employee.setTelephone("13998337244");
            employee.setUsername("employee" + i);
            employee.setPassword("employee");
            employee.setDeleted(false);

            int t = i % 3;
            if (t == 0) {
                employee.setType(EmployeeType.DOCTOR);

            } else if (t == 1) {

                employee.setType(EmployeeType.NURSE);
            } else {

                employee.setType(EmployeeType.CAREWORKER);
            }


            list.add(employee);
        }


        try {
            FileUtils.write(JsonUtils.serialize(list), "data/employee.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
