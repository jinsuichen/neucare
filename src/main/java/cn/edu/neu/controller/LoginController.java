package cn.edu.neu.controller;

import cn.edu.neu.util.FxUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField testField;

    @FXML
    public Button loginButton;

    public LoginController(){

    }

    @FXML
    public void initialize(){



        //TODO 使用lambda表达式改写
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO 设置用户名密码的检验
                boolean flag= true;


                if(flag){

                    // 获取框架
                    AnchorPane top =  FxUtils.loadItem("fxml/top.fxml");
                    AnchorPane left = FxUtils.loadItem("fxml/user/left.fxml");
                    AnchorPane center = FxUtils.loadItem("fxml/user/center.fxml");

                    BorderPane root = FxUtils.loadRoot();
                    root.setLeft(left);
                    root.setCenter(center);
                    root.setTop(top);

                    Scene scene = new Scene(root);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    //scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");


                }
            }
        });


    }
}
