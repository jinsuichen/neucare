package cn.edu.neu.util;

import cn.edu.neu.pojo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
        createQuestion();
        createTemplate();
        creatEvaluationInfo();


    }

    public static void creatEvaluationInfo(){
        List<EvaluationInfo> list = new ArrayList<>();
        EvaluationInfo evaluationInfo = new EvaluationInfo();
        evaluationInfo.setEid(1);
        evaluationInfo.setPid(1);
        evaluationInfo.setTid(1);
        evaluationInfo.setIid(1);
        evaluationInfo.setTime("now");
        evaluationInfo.setSuggestion("热死的");
        evaluationInfo.setDeleted(false);
        list.add(evaluationInfo);

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/evaluationInfos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createTemplate() {
        Template template1 = new Template();
        template1.setTid(1);
        template1.setName("模板1");
        template1.setType("A");
        template1.setDeleted(false);

        Template template2 = new Template();
        template2.setTid(2);
        template2.setName("模板2");
        template2.setType("B");
        template2.setDeleted(false);

        Template template3 = new Template();
        template3.setTid(3);
        template3.setName("模板3");
        template3.setType("D");
        template3.setDeleted(false);

        List<Template> list = new ArrayList<>();
        list.add(template1);
        list.add(template2);
        list.add(template3);

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/templates.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createQuestion() {
        List<Question> list = new ArrayList<>();
        String[] titleArr = {"嘉然今天吃什么", "1+1等于几", "爸爸的爸爸叫什么", "爸爸的妈妈叫什么",
                "沈阳在哪个省份", "水是剧毒的吗", "linux操作系统的创始人是谁", "世界上最热的地方在哪",
                "三句话让男人为我花了多少", "蜜雪冰城什么味道", "儿童节要给谁来一刀狠的", "下面三个人哪个最逊",
                "两面包夹什么", "为什么不能吃三只松鼠", "谁是傻逼", "为什么要学JavaFX", "地球人有几个眼睛",
                "吃着碗里的下一句", "下面哪个地方经济最发达", "非正常人类多少摄氏度"};
        String[] choiceArr1 = {"夹心糖", "2", "陈锦绥", "奶奶",
                "辽宁省", "是", "linus", "东软睿道",
                "18万", "甜蜜蜜", "曹冲", "彬彬",
                "芝士", "不能吃野味", "我", "被逼无奈", "两个",
                "望着锅里的", "曹县", "105度"};
        String[] choiceArr2 = {"饭", "3", "爷爷", "爷爷",
                "黑龙江省", "不是", "linux", "赤道",
                "28万", "天灭你", "郭橐驼", "阿伟",
                "奶酪", "不好吃", "我", "了解图形界面的知识", "一个或两个",
                "吃着锅里的", "北京", "37度"};
        String[] choiceArr3 = {"两面包夹芝士", "？？？什么傻逼题", "爸爸", "孙子",
                "东北省", "我不知道", "陈锦绥", "105度的你",
                "38万", "你学编程天灭你", "陈锦绥", "杰哥",
                "JavaFX", "松鼠会吃人", "我", "我爱JavaFX", "一个",
                "往锅里吐", "上海", "100度"};

        for (int i = 1; i <= 20; i++) {
            Question question = new Question();
            question.setTitle(titleArr[i - 1]);
            question.setChoice1(choiceArr1[i - 1]);
            question.setChoice2(choiceArr2[i - 1]);
            question.setChoice3(choiceArr3[i - 1]);
            question.setQid(i);
            question.setDeleted(false);

            if (i <= 8) {
                question.setTid(1);
            } else if (i <= 14) {
                question.setTid(2);
            } else {
                question.setTid(3);
            }

            question.setType("A");

            list.add(question);
        }

        try {
            FileUtils.write(JsonUtils.serialize(list), "data/questions.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void createBed() {
        ArrayList<Bed> list = new ArrayList<>();

        int bedId = 1;

        //一共42个房间，为每个房间添加4张床
        for (int i = 1; i <= 42; i++) {
            for (int j = 1; j <= 4; j++) {
                Bed bed = new Bed();
                bed.setBid(bedId++);
                bed.setName(j + "床");
                bed.setWid(i);
                bed.setDeleted(false);

                //设置患者
                if (j == 1)
                    bed.setPid(i);
                else if (j == 2)
                    bed.setPid(42 + i);

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
                if (i <= 6) ward.setName(i + "0" + j);
                else if (i <= 9) ward.setName(i - 6 + "0" + j);
                else ward.setName(i - 9 + "0" + j);
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
            floor.setName(i + "层");
            floor.setDeleted(false);
            list.add(floor);
        }

        //为二号楼添加数据
        for (int i = 7; i <= 9; i++) {
            Floor floor = new Floor();
            floor.setFid(i);
            floor.setSid(2);
            floor.setName(i - 6 + "层");
            floor.setDeleted(false);
            list.add(floor);
        }

        //为三号楼添加信息
        for (int i = 10; i <= 14; i++) {
            Floor floor = new Floor();
            floor.setFid(i);
            floor.setSid(3);
            floor.setName(i - 9 +  "层");
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
