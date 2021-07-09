package cn.edu.neu.po;

import cn.edu.neu.pojo.Admin;
import cn.edu.neu.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class AdminData {

    public static List<Admin> adminData;


    static{
        //adminData = JsonUtils.parseFromFile("data/admin.json", Admin[].class);
    }

    public static void main(String[] args) {
        System.out.println(adminData);
        System.out.println(adminData.getClass());
        System.out.println(adminData.get(0).getClass());
    }

}
