package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.util.FxUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TopController {

    @FXML
    private AnchorPane top;

    @FXML
    private Label overview;

    @FXML
    private Label user;

    @FXML
    private Label evaluation;

    @FXML
    private Label structure;

    @FXML
    private Label greeting;


    public TopController() {
    }

    @FXML
    public void initialize(){


        /*// 阴影向内
        InnerShadow innerShadow = new InnerShadow();
        // 颜色蔓延的距离
        innerShadow.setRadius(6);
        // OffsetX正，则从左至右向内；负，则从右至左向内
        innerShadow.setOffsetX(-2);
        // OffsetY正，则从上至下向内；负，则从下至上向内
        innerShadow.setOffsetY(-2);
        innerShadow.setColor(Color.GRAY);
        top.setEffect(innerShadow);*/

        //top.setStyle("-fx-border-color: white,white,white,black;");
        top.setStyle("-fx-border-color: white white grey white");




        Status.overview = overview;
        Status.user = user;
        Status.evaluation = evaluation;
        Status.structure = structure;

        greeting.setText("你好，" + Status.currentUsername);

        //TODO 将上部标签的点击事件封装为方法
        FxUtils.changeCenter(Status.root, "overview", "hospital");
        Status.lbl1.setText("医院");
        Status.lbl2.setText("");
        Status.lbl3.setText("");
        Status.lbl4.setText("");
        Status.lbl5.setText("");


        overview.setStyle("-fx-text-fill: #2585a6");
        Status.lbl1.setStyle("-fx-text-fill: #2585a6");



        overview.setOnMouseClicked(event -> {
            if("overview".equals(Status.category1)){
                //do nothing
            }else{

                FxUtils.changeCenter(Status.root, "overview", "hospital");
                Status.lbl1.setText("医院");
                Status.lbl2.setText("");
                Status.lbl3.setText("");
                Status.lbl4.setText("");
                Status.lbl5.setText("");
            }
        });

        structure.setOnMouseClicked(event -> {
            if("structure".equals(Status.category1)){
                //do nothing
            }else{

                FxUtils.changeCenter(Status.root, "structure", "building");
                Status.lbl1.setText("楼宇管理");
                Status.lbl2.setText("");
                Status.lbl3.setText("");
                Status.lbl4.setText("");
                Status.lbl5.setText("");
            }

        });

        user.setOnMouseClicked(event -> {
            if("user".equals(Status.category1)){
                //do nothing
            }else{

                FxUtils.changeCenter(Status.root, "user", "patient");
                Status.lbl1.setText("病患管理");
                Status.lbl2.setText("床位管理");
                Status.lbl3.setText("稀有设备管理");
                Status.lbl4.setText("");
                Status.lbl5.setText("");
            }
        });

        evaluation.setOnMouseClicked(event -> {
            if("evaluation".equals(Status.category1)){
                //do nothing
            }else{

                FxUtils.changeCenter(Status.root, "evaluation", "template");

                Status.lbl1.setText("模板管理");
                Status.lbl2.setText("问题管理");
                Status.lbl3.setText("");
                Status.lbl4.setText("");
                Status.lbl5.setText("");
            }
        });




    }
}
