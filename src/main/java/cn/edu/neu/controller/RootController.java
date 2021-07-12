package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.util.FileUtils;
import cn.edu.neu.util.JsonUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class RootController {
    @FXML
    private BorderPane root;


    public RootController() {
    }

    @FXML
    public void initialize() {
        Status.root = this.root;

        root.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");



    }
}
