package cn.edu.neu.controller;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EvaluationInfo;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.EmployeeService;
import cn.edu.neu.service.EvaluationInfoService;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.TemplateService;
import cn.edu.neu.service.impl.EmployeeServiceImpl;
import cn.edu.neu.service.impl.EvaluationInfoServiceImpl;
import cn.edu.neu.service.impl.PatientServiceImpl;
import cn.edu.neu.service.impl.TemplateServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class EvaluationController {
    @FXML
    private TableView tableView;
    @FXML
    private Button deleteButton;

    public EvaluationController() {
    }

    private ObservableList<EvaluationInfo> list = FXCollections.observableArrayList();

    private final EvaluationInfoService evaluationInfoService = new EvaluationInfoServiceImpl();
    private final PatientService patientService = new PatientServiceImpl();
    private final TemplateService templateService = new TemplateServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @FXML
    public void initialize() {

        //为可观察列表添加数据
        list.addAll(evaluationInfoService.getAllEvaluationInfos());
        //将可观察列表与表格绑定
        tableView.setItems(list);


        TableColumn<EvaluationInfo, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<EvaluationInfo, String> genderColumn = new TableColumn<>("性别");
        TableColumn<EvaluationInfo, String> templateNameColumn = new TableColumn<>("模板名称");
        TableColumn<EvaluationInfo, String> templateTypeColumn = new TableColumn<>("模板类型");
        TableColumn<EvaluationInfo, String> timeColumn = new TableColumn<>("评估时间");
        TableColumn<EvaluationInfo, String> employeeColumn = new TableColumn<>("评估人");
        TableColumn<EvaluationInfo, String> suggestionColumn = new TableColumn<>("建议");

        nameColumn.setPrefWidth(139);
        genderColumn.setPrefWidth(139);
        templateNameColumn.setPrefWidth(139);
        templateTypeColumn.setPrefWidth(139);
        timeColumn.setPrefWidth(139);
        employeeColumn.setPrefWidth(139);
        suggestionColumn.setPrefWidth(139);

        nameColumn.setStyle("-fx-alignment: CENTER;");
        genderColumn.setStyle("-fx-alignment: CENTER;");
        templateNameColumn.setStyle("-fx-alignment: CENTER;");
        templateTypeColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setStyle("-fx-alignment: CENTER;");
        employeeColumn.setStyle("-fx-alignment: CENTER;");
        suggestionColumn.setStyle("-fx-alignment: CENTER;");



        //显示表格信息
        //TODO 将方法写进EvaluationInfoDao中
        nameColumn.setCellValueFactory(param -> {
            Integer pid = param.getValue().getPid();
            Patient patient = patientService.getPatientByPid(pid);
            return new SimpleStringProperty(patient.getName());
        });

        genderColumn.setCellValueFactory(param -> {
            Integer pid = param.getValue().getPid();
            Patient patient = patientService.getPatientByPid(pid);
            return new SimpleStringProperty(patient.getGender());
        });

        templateNameColumn.setCellValueFactory(param -> {
            Integer tid = param.getValue().getTid();
            Template template = templateService.getTemplateByTid(tid);
            return new SimpleStringProperty(template.getName());
        });

        templateTypeColumn.setCellValueFactory(param -> {
            Integer tid = param.getValue().getTid();
            Template template = templateService.getTemplateByTid(tid);
            return new SimpleStringProperty(template.getType());
        });

        employeeColumn.setCellValueFactory(param -> {
            Integer eid = param.getValue().getEid();
            Employee employee = employeeService.getEmployeeByEid(eid);
            return new SimpleStringProperty(employee.getName());
        });

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        suggestionColumn.setCellValueFactory(new PropertyValueFactory<>("suggestion"));


        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(genderColumn);
        tableView.getColumns().add(templateNameColumn);
        tableView.getColumns().add(templateTypeColumn);
        tableView.getColumns().add(timeColumn);
        tableView.getColumns().add(employeeColumn);
        tableView.getColumns().add(suggestionColumn);

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                List<Object> itemList = new ArrayList<>();
                itemList.addAll(selectedItems);

                for (Object o : itemList) {
                    EvaluationInfo evaluationInfo = (EvaluationInfo) o;
                    evaluationInfoService.deleteEvaluationInfoByIid(evaluationInfo.getIid());
                    list.remove(evaluationInfo);
                }
            }
        });

    }
}
