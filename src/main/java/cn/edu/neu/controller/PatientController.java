package cn.edu.neu.controller;

import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Patient;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import javax.xml.crypto.Data;

public class PatientController {

    @FXML
    private TableView tableView;

    @FXML
    private TextField birthday;

    @FXML
    private TextField name;

    @FXML
    private TextField gender;

    @FXML
    private TextField telephone;

    @FXML
    private TextField emergencyTelephone;

    @FXML
    private TextField emergencyContact;

    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button evaluateButton;


    public PatientController() {
    }


    @FXML
    public void initialize() {


        //创建可观察列表
        ObservableList<Patient> list = FXCollections.observableArrayList();
        //为可观察列表添加数据
        list.addAll(DataBase.patientData);
        //将可观察列表与表格绑定
        tableView.setItems(list);


        TableColumn<Patient, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Patient, Number> ageColumn = new TableColumn<>("年龄");
        TableColumn<Patient, String> genderColumn = new TableColumn<>("性别");
        TableColumn<Patient, String> telephoneColumn = new TableColumn<>("联系电话");
        TableColumn<Patient, String> emergencyContactColumn = new TableColumn<>("紧急联系人");
        TableColumn<Patient, String> emergencyTelephoneColumn = new TableColumn<>("紧急联系电话");

        nameColumn.setCellValueFactory(param -> {
            SimpleStringProperty name = new SimpleStringProperty(param.getValue().getName());
            return name;
        });
        ageColumn.setCellValueFactory(param -> {
            SimpleIntegerProperty age = new SimpleIntegerProperty(param.getValue().getAge());
            return age;
        });

        genderColumn.setCellValueFactory(param -> {
            SimpleStringProperty gender = new SimpleStringProperty(param.getValue().getGender());
            return gender;
        });
        telephoneColumn.setCellValueFactory(param -> {
            SimpleStringProperty telephone = new SimpleStringProperty(param.getValue().getTelephone());
            return telephone;
        });
        emergencyContactColumn.setCellValueFactory(param -> {
            SimpleStringProperty emergencyContact = new SimpleStringProperty(param.getValue().getEmergencyContact());
            return emergencyContact;
        });
        emergencyTelephoneColumn.setCellValueFactory(param -> {
            SimpleStringProperty emergencyTelephone = new SimpleStringProperty(param.getValue().getEmergencyTelephone());
            return emergencyTelephone;
        });



        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(ageColumn);
        tableView.getColumns().add(genderColumn);
        tableView.getColumns().add(telephoneColumn);
        tableView.getColumns().add(emergencyContactColumn);
        tableView.getColumns().add(emergencyTelephoneColumn);
    }

}
