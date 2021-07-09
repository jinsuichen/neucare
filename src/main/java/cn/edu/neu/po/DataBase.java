package cn.edu.neu.po;

import cn.edu.neu.pojo.*;
import cn.edu.neu.util.FileUtils;
import cn.edu.neu.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DataBase {

    public static List<Admin> adminData;
    public static List<Bed> bedData;
    public static List<Employee> employeeData;
    public static List<Floor> floorData;
    public static List<Patient> patientData;
    public static List<Question> questionData;
    public static List<RareEquipment> rareEquipmentData;
    public static List<Structure> structureData;

    //FIXME 抽取为方法

    static{
        adminData = JsonUtils.parseList(FileUtils.toString("data/admin.json"), Admin.class);
        //FIXME 判断空指针
        Collections.sort(adminData);

        bedData = JsonUtils.parseList(FileUtils.toString("data/bed.json"), Bed.class);
        employeeData = JsonUtils.parseList(FileUtils.toString("data/employee.json"), Employee.class);
        floorData = JsonUtils.parseList(FileUtils.toString("data/floor.json"), Floor.class);
        patientData = JsonUtils.parseList(FileUtils.toString("data/patient.json"), Patient.class);
        questionData = JsonUtils.parseList(FileUtils.toString("data/question.json"), Question.class);
        rareEquipmentData = JsonUtils.parseList(FileUtils.toString("data/rareEquipment.json"), RareEquipment.class);
        structureData = JsonUtils.parseList(FileUtils.toString("data/structure.json"), Structure.class);
    }


}
