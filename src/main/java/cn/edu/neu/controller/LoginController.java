package cn.edu.neu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField testField;

    public LoginController(){

    }

    @FXML
    public void initialize(){
        System.out.println("test");
    }
}
