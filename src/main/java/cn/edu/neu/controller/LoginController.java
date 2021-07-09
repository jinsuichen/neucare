package cn.edu.neu.controller;

import cn.edu.neu.util.FxUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public PasswordField passwordField;

    @FXML
    public TextField testField;

    @FXML
    public Button loginButton;

    @FXML
    public AnchorPane root;


    public LoginController(){

    }

    @FXML
    public void initialize(){

        loginButton.setOnAction(event -> {

            //TODO 设置用户名密码的检验
            boolean flag= true;


            if(flag){

                //关闭登陆界面
                Stage oldStage = (Stage) root.getScene().getWindow();
                oldStage.close();

                //显示主界面
                BorderPane root = FxUtils.getTotalPane("user", "patient");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();


                //scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

            }
        });


    }
}
