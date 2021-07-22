package cn.edu.neu.controller;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.service.EmployeeService;
import cn.edu.neu.service.impl.EmployeeServiceImpl;
import cn.edu.neu.util.FxDialogUtils;
import cn.edu.neu.util.FxLoadNodeUtils;
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
import java.util.regex.Pattern;

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

    /**
     * 表格数据源
     */
    private ObservableList<Employee> tableViewList = FXCollections.observableArrayList();

    private EmployeeService employeeService = new EmployeeServiceImpl();


    @FXML
    public void initialize() {

        //为可观察列表添加数据
        tableViewList.addAll(employeeService.getAllEmployees());
        //将可观察列表与表格绑定
        tableView.setItems(tableViewList);


        //创建列
        TableColumn<Employee, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Employee, String> usernameColumn = new TableColumn<>("用户名");
        TableColumn<Employee, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Employee, String> birthdayColumn = new TableColumn<>("出生日期");
        TableColumn<Employee, String> typeColumn = new TableColumn<>("职称");
        TableColumn<Employee, String> expertSkillColumn = new TableColumn<>("专长");
        TableColumn<Employee, String> telephoneColumn = new TableColumn<>("电话");
        TableColumn<Employee, String> identificationNumberColumn = new TableColumn<>("身份证号码");

        //添加数据
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Number>("eid"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
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
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
        expertSkillColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("expertSkill"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephone"));
        identificationNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("identificationNumber"));

        //为表格添加列
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

        //设置列宽
        idColumn.setPrefWidth(50);
        nameColumn.setPrefWidth(153);
        birthdayColumn.setPrefWidth(153);
        typeColumn.setPrefWidth(153);
        expertSkillColumn.setPrefWidth(153);
        telephoneColumn.setPrefWidth(153);
        identificationNumberColumn.setPrefWidth(153);

        //设置居中
        idColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setStyle("-fx-alignment: CENTER;");
        birthdayColumn.setStyle("-fx-alignment: CENTER;");
        typeColumn.setStyle("-fx-alignment: CENTER;");
        expertSkillColumn.setStyle("-fx-alignment: CENTER;");
        telephoneColumn.setStyle("-fx-alignment: CENTER;");
        identificationNumberColumn.setStyle("-fx-alignment: CENTER;");

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
                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/addEmployee.fxml");
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

                typeBox.getItems().add("医生");
                typeBox.getItems().add("护士");
                typeBox.getItems().add("护工");


                stage.show();



                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String errMsg = "";
                        boolean flag = true;

                        String username = usernameField.getText();
                        String password = passwordField.getText();
                        String name = nameField.getText();
                        Object typeObj = typeBox.getValue();
                        String birthday = birthdayField.getText();
                        String expertSkill = expertSkillField.getText();
                        String telephone = telephoneField.getText();
                        String identificationNumber = identificationNumberField.getText();

                        if(username == null || "".equals(username)){
                            flag = false;
                            errMsg += "用户名不能为空\n";
                        }
                        if(password == null || "".equals(password)){
                            flag = false;
                            errMsg += "密码不能为空\n";
                        }
                        if(name == null || "".equals(name)){
                            flag = false;
                            errMsg += "姓名不能为空\n";
                        }
                        if(typeObj == null){
                            flag = false;
                            errMsg += "您未选择职称\n";
                        }
                        if(birthday == null || "".equals(birthday)){
                            flag = false;
                            errMsg += "生日不能为空\n";
                        }
                        if(expertSkill == null || "".equals(expertSkill)){
                            flag = false;
                            errMsg += "专长不能为空\n";
                        }
                        if(!Pattern.matches("^([1]\\d{10}|([\\(（]?0[0-9]{2,3}[）\\)]?[-]?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)$", telephone)){
                            flag = false;
                            errMsg += "电话号码填写有误\n";
                        }
                        if(!Pattern.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", identificationNumber)){
                            flag = false;
                            errMsg += "身份证号码填写有误\n";
                        }

                        if(flag){
                            Employee employee = new Employee();
                            employee.setUsername(username);
                            employee.setPassword(password);
                            employee.setName(name);
                            String value = (String) typeObj;
                            EmployeeType type = null;
                            if ("医生".equals(value)) {
                                type = EmployeeType.DOCTOR;
                            } else if ("护士".equals(value)) {
                                type = EmployeeType.NURSE;
                            } else if ("护工".equals(value)) {
                                type = EmployeeType.CAREWORKER;
                            }
                            employee.setType(type);
                            employee.setBirthday(birthday);
                            employee.setExpertSkill(expertSkill);
                            employee.setTelephone(telephone);
                            employee.setIdentificationNumber(identificationNumber);

                            employeeService.addEmployee(employee);
                            tableViewList.add(employee);
                            stage.close();

                        }else{
                            FxDialogUtils.showMessageDialog((Stage) anchorPane.getScene().getWindow(), errMsg, "信息有误");
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
