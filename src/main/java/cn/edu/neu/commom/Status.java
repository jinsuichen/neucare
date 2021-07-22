package cn.edu.neu.commom;

import cn.edu.neu.pojo.Employee;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Status {


    //重要信息

    /**
     * 当前用户是否是管理员
     */
    public static boolean isAdmin;

    /**
     * 当前用户的用户名
     */
    public static String currentUsername;

    /**
     * 当前员工
     */
    public static Employee currentEmployee;

    /**
     * 一级分类
     */
    public static String category1;

    /**
     * 二级分类
     */
    public static String category2;

    /**
     * 根结点
     */
    public static BorderPane root;


    //布局信息

    /**
     * 总览
     */
    public static Label overview;

    /**
     * 用户管理
     */
    public static Label user;

    /**
     * 建筑管理
     */
    public static Label structure;

    /**
     * 评估管理
     */
    public static Label evaluation;

    /**
     * 左侧label1
     */
    public static Label lbl1;

    /**
     * 左侧label2
     */
    public static Label lbl2;

    /**
     * 左侧label3
     */
    public static Label lbl3;

    /**
     * 左侧label4
     */
    public static Label lbl4;

    /**
     * 左侧label5
     */
    public static Label lbl5;




    //非重要信息

    /**
     * 当前用户是否是管理员
     */
    public static int totalPatient;

    /**
     * 当前用户是否是管理员
     */
    public static int totalEmployee;





}
