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

        /*Stage stage = (Stage) root.getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            try {
                FileUtils.write(JsonUtils.serialize(DataBase.adminData), "data/admin.json");
                FileUtils.write(JsonUtils.serialize(DataBase.patientData), "data/patient.json");
                FileUtils.write(JsonUtils.serialize(DataBase.bedData), "data/bed.json");
                FileUtils.write(JsonUtils.serialize(DataBase.employeeData), "data/employee.json");
                FileUtils.write(JsonUtils.serialize(DataBase.floorData), "data/floor.json");
                FileUtils.write(JsonUtils.serialize(DataBase.questionData), "data/question.json");
                FileUtils.write(JsonUtils.serialize(DataBase.rareEquipmentData), "data/rareEquipment.json");
                FileUtils.write(JsonUtils.serialize(DataBase.structureData), "data/structure.json");
                System.exit(0);
            } catch (IOException e) {
                System.err.println("出现严重错误：数据未得到有效保存！");
                e.printStackTrace();
            }
        });*/


    }
}
