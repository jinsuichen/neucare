package cn.edu.neu.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label labelName = new Label("用户名: ");
        Label labelPassword = new Label("密   码: ");

        TextField textFieldName = new TextField();
        PasswordField passwordFieldPassWord = new PasswordField();

        Button buttonLogin = new Button("登录");
        Button buttonClear = new Button("清除");

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);

        gp.add(labelName, 0, 0);
        gp.add(textFieldName, 1, 0);
        gp.add(labelPassword, 0, 1);
        gp.add(passwordFieldPassWord, 1, 1);
        gp.add(buttonClear,0, 2);
        gp.add(buttonLogin, 1, 2);

        gp.setStyle("-fx-background-color: #ffc8b8");
        gp.setVgap(15);
        gp.setHgap(10);
        GridPane.setMargin(buttonLogin, new Insets(0, 0, 0, 100));


        //清除按钮
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldName.setText("");
                passwordFieldPassWord.setText("");
            }
        });

        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });


        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("登录");
        primaryStage.setScene(new Scene(gp));
        primaryStage.show();
    }
}


