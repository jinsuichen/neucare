package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.util.FxLoadNodeUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LeftController {
    @FXML
    private AnchorPane left;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl5;

    public LeftController() {
    }

    @FXML
    public void initialize(){

        left.setStyle("-fx-border-color: grey grey white white");


        Status.lbl1 = lbl1;
        Status.lbl2 = lbl2;
        Status.lbl3 = lbl3;
        Status.lbl4 = lbl4;
        Status.lbl5 = lbl5;

        lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if("user".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "patient");
                }else if("evaluation".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "template");
                }
            }
        });


        lbl2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if("user".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "bed");
                }else if("evaluation".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "question");
                }
            }
        });


        lbl3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if("user".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "rareEquipment");
                }else if("evaluation".equals(Status.category1)){
                    FxLoadNodeUtils.changeCenter(Status.root, Status.category1, "evaluation");
                }
            }
        });

    }
}
