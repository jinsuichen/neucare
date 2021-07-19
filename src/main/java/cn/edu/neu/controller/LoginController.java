package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.service.impl.AdminServiceImpl;
import cn.edu.neu.service.impl.EmployeeServiceImpl;
import cn.edu.neu.util.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField testField;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane root;


    public LoginController(){
    }

    @FXML
    public void initialize(){

        loginButton.setOnAction(event -> {

            //进行登录检验

            //是否为管理员
            boolean flag1 = new AdminServiceImpl().login(testField.getText(), passwordField.getText());
            //是否为普通用户
            boolean flag2 = new EmployeeServiceImpl().login(testField.getText(), passwordField.getText());

            //FIXME 方便测试，登录恒为真，记得删除
            flag2 = true;
            //flag1 = true;


            //FIXME isAdmin状态可能用不到
            //FiXME 提取重复语句
            if(flag1){
                Status.isAdmin = true;

                //关闭登陆界面
                Stage oldStage = (Stage) root.getScene().getWindow();
                oldStage.close();

                //进入超级管理员界面
                AnchorPane anchorPane = (AnchorPane) FxUtils.loadNode("fxml/other/admin.fxml");
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);

                stage.show();

            }else if(flag2){
                Status.isAdmin = false;

                //进入普通用户界面
                Status.currentUsername = testField.getText();
                Status.category1="overview";
                Status.category2="hospital";

                //关闭登陆界面
                Stage oldStage = (Stage) root.getScene().getWindow();
                oldStage.close();

                //显示主界面
                BorderPane root = FxUtils.getTotalPane("overview", "hospital");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }else{
                //TODO 验证失败进行弹窗提示

            }



        });


    }
}
