package cn.edu.neu.po;

import cn.edu.neu.pojo.*;
import cn.edu.neu.util.FileUtils;
import cn.edu.neu.util.JsonUtils;

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
    public static List<Ward> wardData;
    public static List<Template> templateData;

    //FIXME 抽取为方法

    static{
        adminData = JsonUtils.parseList(FileUtils.toString("data/admins.json"), Admin.class);
        bedData = JsonUtils.parseList(FileUtils.toString("data/beds.json"), Bed.class);
        wardData = JsonUtils.parseList(FileUtils.toString("data/wards.json"), Ward.class);
        employeeData = JsonUtils.parseList(FileUtils.toString("data/employees.json"), Employee.class);
        floorData = JsonUtils.parseList(FileUtils.toString("data/floors.json"), Floor.class);
        patientData = JsonUtils.parseList(FileUtils.toString("data/patients.json"), Patient.class);
        questionData = JsonUtils.parseList(FileUtils.toString("data/questions.json"), Question.class);
        templateData = JsonUtils.parseList(FileUtils.toString("data/templates.json"), Template.class);
        //rareEquipmentData = JsonUtils.parseList(FileUtils.toString("data/rareEquipments.json"), RareEquipment.class);
        structureData = JsonUtils.parseList(FileUtils.toString("data/structures.json"), Structure.class);
    }

}
