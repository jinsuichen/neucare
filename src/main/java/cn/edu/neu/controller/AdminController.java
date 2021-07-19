package cn.edu.neu.controller;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.service.EmployeeService;
import cn.edu.neu.service.impl.EmployeeServiceImpl;
import cn.edu.neu.util.FxUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @FXML
    private TableView tableView;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField textField;
    @FXML
    private Button queryButton;
    @FXML
    private ComboBox<String> comboBox;

    public AdminController() {
    }

    private ObservableList<Employee> tableViewList = FXCollections.observableArrayList();

    private EmployeeService employeeService = new EmployeeServiceImpl();


    @FXML
    public void initialize() {

        //为可观察列表添加数据
        tableViewList.addAll(employeeService.getAllEmployees());
        //将可观察列表与表格绑定
        tableView.setItems(tableViewList);


        TableColumn<Employee, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Employee, String> usernameColumn = new TableColumn<>("用户名");
        TableColumn<Employee, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Employee, String> birthdayColumn = new TableColumn<>("出生日期");
        TableColumn<Employee, String> typeColumn = new TableColumn<>("职称");
        TableColumn<Employee, String> expertSkillColumn = new TableColumn<>("专长");
        TableColumn<Employee, String> telephoneColumn = new TableColumn<>("电话");
        TableColumn<Employee, String> identificationNumberColumn = new TableColumn<>("身份证号码");

        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Number>("eid"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        birthdayColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> param) {
                EmployeeType type = param.getValue().getType();
                String str = null;
                if (type.equals(EmployeeType.DOCTOR)) {
                    str = "医生";
                } else if (type.equals(EmployeeType.NURSE)) {
                    str = "护士";
                } else if (type.equals(EmployeeType.CAREWORKER)) {
                    str = "护工";
                } else {
                    str = "未知";
                }

                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });
//        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, EmployeeType>("type"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
        expertSkillColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("expertSkill"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephone"));
        identificationNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("identificationNumber"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(usernameColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(birthdayColumn);
        tableView.getColumns().add(typeColumn);
        tableView.getColumns().add(expertSkillColumn);
        tableView.getColumns().add(telephoneColumn);
        tableView.getColumns().add(identificationNumberColumn);


        //设置更改模式
        tableView.setEditable(true);
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        birthdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        expertSkillColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        identificationNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //绑定提交事件 当更改提交时，进行保存
        usernameColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        nameColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        birthdayColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        typeColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        expertSkillColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        telephoneColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        identificationNumberColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //模糊查询功能
        queryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String keyword = textField.getText();

                tableViewList.remove(0, tableViewList.size());

                if (keyword == null || keyword.isEmpty()) {
                    tableViewList.addAll(employeeService.getAllEmployees());
                } else {
                    tableViewList.addAll(employeeService.fuzzyQueryEmployees(keyword));
                }

                comboBox.setValue("全部");

            }
        });

        comboBox.getItems().add("全部");
        comboBox.getItems().add("医生");
        comboBox.getItems().add("护士");
        comboBox.getItems().add("护工");
        comboBox.setValue("全部");

        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.setText("");

                String value = comboBox.getValue();
                List<Employee> list = null;
                if ("全部".equals(value)) {
                    list = employeeService.getAllEmployees();
                } else if ("医生".equals(value)) {
                    list = employeeService.getEmployeesByType(EmployeeType.DOCTOR);
                } else if ("护士".equals(value)) {
                    list = employeeService.getEmployeesByType(EmployeeType.NURSE);
                } else if ("护工".equals(value)) {
                    list = employeeService.getEmployeesByType(EmployeeType.CAREWORKER);
                }
                tableViewList.remove(0, tableViewList.size());
                tableViewList.addAll(list);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                List<Object> itemList = new ArrayList<>();
                itemList.addAll(selectedItems);

                for (Object o : itemList) {
                    Employee employee = (Employee) o;
                    employeeService.deleteEmployeeByEid(employee.getEid());
                    tableViewList.remove(employee);
                }
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane anchorPane = (AnchorPane) FxUtils.loadNode("fxml/other/addEmployee.fxml");
                Stage stage = new Stage();
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);


                TextField usernameField = (TextField) anchorPane.getChildren().get(0);
                TextField passwordField = (TextField) anchorPane.getChildren().get(1);
                TextField nameField = (TextField) anchorPane.getChildren().get(2);
                ComboBox typeBox = (ComboBox) anchorPane.getChildren().get(3);
                TextField birthdayField = (TextField) anchorPane.getChildren().get(4);
                TextField expertSkillField = (TextField) anchorPane.getChildren().get(5);
                TextField telephoneField = (TextField) anchorPane.getChildren().get(6);
                TextField identificationNumberField = (TextField) anchorPane.getChildren().get(7);
                Button submitButton = (Button) anchorPane.getChildren().get(16);
                Button cancelButton = (Button) anchorPane.getChildren().get(17);

                comboBox.getItems().add("医生");
                comboBox.getItems().add("护士");
                comboBox.getItems().add("护工");
                comboBox.setValue("医生");


                stage.show();


                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String errMsg = "";

                        //TODO 表单检验
                        Employee employee = new Employee();
                        employee.setUsername(usernameField.getText());
                        employee.setPassword(passwordField.getText());
                        employee.setName(nameField.getText());
                        String value = (String) typeBox.getValue();
                        EmployeeType type = EmployeeType.DOCTOR;
                        if ("医生".equals(value)) {
                            type = EmployeeType.DOCTOR;
                        } else if ("护士".equals(value)) {
                            type = EmployeeType.NURSE;
                        } else if ("护工".equals(value)) {
                            type = EmployeeType.CAREWORKER;
                        }
                        employee.setType(type);
                        employee.setBirthday(birthdayField.getText());
                        employee.setExpertSkill(expertSkillField.getText());
                        employee.setTelephone(telephoneField.getText());
                        employee.setIdentificationNumber(identificationNumberField.getText());


                        if (employeeService.addEmployee(employee)) {
                            //如果添加成功，进行数据展示并关闭窗口
                            tableViewList.add(employee);
                            stage.close();
                        }
                    }
                });

                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });


            }
        });


    }
}
