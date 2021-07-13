package cn.edu.neu.controller;

import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.impl.PatientServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

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

    private final PatientService patientService = new PatientServiceImpl();

    private final ObservableList<Patient> list = FXCollections.observableArrayList();


    //FIXME 这个方法好像没什么卵用
    private void loadTable(){
        for(Patient p : DataBase.patientData){
            if(!p.isDeleted()){
                list.add(p);
            }
        }
    }

    public PatientController() {
    }


    @FXML
    public void initialize() {


        //创建可观察列表
        ObservableList<Patient> list = FXCollections.observableArrayList();
        //为可观察列表添加数据
        for(Patient p : DataBase.patientData){
            if(!p.isDeleted()){
                list.add(p);
            }
        }
        //将可观察列表与表格绑定
        tableView.setItems(list);


        TableColumn<Patient, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Patient, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Patient, Number> ageColumn = new TableColumn<>("年龄");
        TableColumn<Patient, String> genderColumn = new TableColumn<>("性别");
        TableColumn<Patient, String> telephoneColumn = new TableColumn<>("联系电话");
        TableColumn<Patient, String> emergencyContactColumn = new TableColumn<>("紧急联系人");
        TableColumn<Patient, String> emergencyTelephoneColumn = new TableColumn<>("紧急联系电话");

        idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Number>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Number>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String >("telephone"));
        emergencyContactColumn.setCellValueFactory(new PropertyValueFactory<Patient, String >("emergencyContact"));
        emergencyTelephoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String >("emergencyTelephone"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(ageColumn);
        tableView.getColumns().add(genderColumn);
        tableView.getColumns().add(telephoneColumn);
        tableView.getColumns().add(emergencyContactColumn);
        tableView.getColumns().add(emergencyTelephoneColumn);


        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object+"";
            }

            @Override
            public Number fromString(String string) {
                try{
                    return Integer.parseInt(string);
                }catch(Exception e){
                    return -1;
                }
            }
        }));
        genderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emergencyContactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emergencyTelephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);





        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Patient patient = new Patient();
                patient.setName(name.getText());
                patient.setGender(gender.getText());
                //FIXME 根据生日设置年龄
                patient.setAge(16);
                patient.setTelephone(telephone.getText());
                patient.setEmergencyTelephone(emergencyTelephone.getText());
                patient.setEmergencyContact(emergencyContact.getText());
                boolean flag = patientService.add(patient);

                if(flag){
                    list.add(patient);
                    tableView.refresh();
                }
            }
        });


        //TODO 删除提示框
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedIndices = tableView.getSelectionModel().getSelectedIndices();

                List<Integer> indexList = new ArrayList<>();

                for(Object index :selectedIndices){
                    indexList.add((int)index);
                }

                for(int i = indexList.size() - 1 ;i>=0; i--){
                    int index = indexList.get(i);
                    int id = list.get(index).getPid();

                    list.remove(index);
                    patientService.deleteById(id);
                }


            }
        });


        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });



    }



}
