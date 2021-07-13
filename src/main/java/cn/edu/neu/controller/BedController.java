package cn.edu.neu.controller;

import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Structure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class BedController {

    @FXML
    private TableView tableView;
    @FXML
    private Button query;
    @FXML
    private Button checkout;
    @FXML
    private ComboBox structure;
    @FXML
    private ComboBox floor;
    @FXML
    private ComboBox ward;
    @FXML
    private Button swap;



    public BedController() {
    }

    @FXML
    public void initialize(){

        ObservableList<Structure> list = FXCollections.observableArrayList();



    }
}
