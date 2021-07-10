package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class RootController {
    @FXML
    public BorderPane root;


    public RootController() {
    }

    @FXML
    public void initialize(){
        Status.root = this.root;
    }
}
