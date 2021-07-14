package cn.edu.neu.util;

import cn.edu.neu.pojo.*;

import java.io.IOException;
import java.util.ArrayList;


//fixme 删除这个类

/**
 * 该类提供将创建数据并写入json文件的方法
 */
public class CreateDataUtils {

    public static void main(String[] args) throws IOException {


        createAdmin();
        createEmployee();
        createPatient();
        creatStructure();
        createFloor();
        createWard();
        createBed();


    }

    public static void createBed(){
        ArrayList<Bed> list = new ArrayList<>();

        int bedId = 1;

        //一共42个房间，为每个房间添加4张床
        for(int i = 1; i<=42; i++){
            for (int j = 1; j <= 4; j++) {
                Bed bed = new Bed();
                bed.setBid(bedId++);
                bed.setName( i + (i <= 9 ? "0" : "") + j + "床");
                bed.setWid(i);
                bed.setDeleted(false);

                //设置患者
                if(j == 1)
                    bed.setPid(i);
                else if( j == 2)
                    bed.setPid(42+i);

                list.add(bed);
            }
        }

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/beds.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createWard() {
        ArrayList<Ward> list = new ArrayList<>();

        int wardId = 1;

        //一共有14个楼层，这里为每个楼层添加三个房间
        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 3; j++) {
                Ward ward = new Ward();
                ward.setDeleted(false);
                ward.setWid(wardId++);
                ward.setFid(i);
                ward.setName("A" + i + (i <= 9 ? "0" : "") + j);
                list.add(ward);
            }
        }


        try {
            FileUtils.write(JsonUtils.serialize(list), "data/wards.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createFloor() {


        ArrayList<Floor> list = new ArrayList<>();

        //为一号楼添加数据
        for (int i = 1; i <= 6; i++) {
            Floor floor = new Floor();
            floor.setFid(i);
            floor.setSid(1);
            floor.setHeight(i);
            floor.setDeleted(false);
            list.add(floor);
        }

        //为二号楼添加数据
        for (int i = 7; i <= 9; i++) {
            Floor floor = new Floor();
            floor.setFid(i);
            floor.setSid(2);
            floor.setHeight(i - 6);
            floor.setDeleted(false);
            list.add(floor);
        }

        //为三号楼添加信息
        for (int i = 10; i <= 14; i++) {
            Floor floor = new Floor();
            floor.setFid(i);
            floor.setSid(3);
            floor.setHeight(i - 9);
            floor.setDeleted(false);
            list.add(floor);
        }

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/floors.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creatStructure() {

        ArrayList<Structure> list = new ArrayList<>();
        Structure structure1 = new Structure();
        structure1.setDeleted(false);
        structure1.setName("门诊楼");
        structure1.setSid(1);

        Structure structure2 = new Structure();
        structure2.setSid(2);
        structure2.setName("急诊楼");
        structure2.setDeleted(false);

        Structure structure3 = new Structure();
        structure3.setSid(3);
        structure3.setName("综合楼");
        structure3.setDeleted(false);


        list.add(structure1);
        list.add(structure2);
        list.add(structure3);


        try {
            FileUtils.write(JsonUtils.serialize(list), "data/structures.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createAdmin() {
        ArrayList<Admin> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Admin admin = new Admin();
            admin.setUsername("admin" + i);
            admin.setPassword("admin");
            list.add(admin);
        }

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/admins.json");
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
            FileUtils.write(JsonUtils.serialize(list), "data/patients.json");
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
            FileUtils.write(JsonUtils.serialize(list), "data/employees.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
