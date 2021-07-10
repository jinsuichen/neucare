package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.util.FxUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TopController {

    @FXML
    public Label overview;

    @FXML
    public Label user;

    @FXML
    public Label evaluation;

    @FXML
    public Label structure;

    @FXML
    public Label greeting;


    public TopController() {
    }

    @FXML
    public void initialize(){

        greeting.setText("你好，" + Status.currentUsername);

        overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if("overview".equals(Status.category1)){
                    //donothing
                }else{

                    FxUtils.changeCenter(Status.root, "overview", "hospital");
                }
            }
        });

        structure.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FxUtils.changeCenter(Status.root, "structure", "building");
            }
        });

        user.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FxUtils.changeCenter(Status.root, "user", "bed");
            }
        });

        evaluation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FxUtils.changeCenter(Status.root, "evaluation", "template");
            }
        });




    }
}
