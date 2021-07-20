package cn.edu.neu.controller;

import cn.edu.neu.commom.Status;
import cn.edu.neu.pojo.EvaluationInfo;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.EvaluationInfoService;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.QuestionService;
import cn.edu.neu.service.TemplateService;
import cn.edu.neu.service.impl.EvaluationInfoServiceImpl;
import cn.edu.neu.service.impl.PatientServiceImpl;
import cn.edu.neu.service.impl.QuestionServiceImpl;
import cn.edu.neu.service.impl.TemplateServiceImpl;
import cn.edu.neu.util.DateUtils;
import cn.edu.neu.util.FxDialogUtils;
import cn.edu.neu.util.FxLoadNodeUtils;
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
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class PatientController {
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<Template> comboBox;
    @FXML
    private TextField searchText;

    @FXML
    private TableView tableView;

    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button evaluateButton;

    private final PatientService patientService = new PatientServiceImpl();
    private final TemplateService templateService = new TemplateServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();
    private final EvaluationInfoService evaluationInfoService = new EvaluationInfoServiceImpl();

    private ObservableList<Patient> list = FXCollections.observableArrayList();


    public PatientController() {
    }


    @FXML
    public void initialize() {


        //为可观察列表添加数据
        list.addAll(patientService.getAllPatients());
        //将可观察列表与表格绑定
        tableView.setItems(list);


        TableColumn<Patient, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Patient, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Patient, Number> ageColumn = new TableColumn<>("年龄");
        TableColumn<Patient, String> genderColumn = new TableColumn<>("性别");
        TableColumn<Patient, String> telephoneColumn = new TableColumn<>("联系电话");
        TableColumn<Patient, String> emergencyContactColumn = new TableColumn<>("紧急联系人");
        TableColumn<Patient, String> emergencyTelephoneColumn = new TableColumn<>("紧急联系电话");

        idColumn.setPrefWidth(50);
        nameColumn.setPrefWidth(153);
        ageColumn.setPrefWidth(153);
        genderColumn.setPrefWidth(153);
        telephoneColumn.setPrefWidth(153);
        emergencyContactColumn.setPrefWidth(153);
        emergencyTelephoneColumn.setPrefWidth(153);

        idColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setStyle("-fx-alignment: CENTER;");
        ageColumn.setStyle("-fx-alignment: CENTER;");
        genderColumn.setStyle("-fx-alignment: CENTER;");
        telephoneColumn.setStyle("-fx-alignment: CENTER;");
        emergencyContactColumn.setStyle("-fx-alignment: CENTER;");
        emergencyTelephoneColumn.setStyle("-fx-alignment: CENTER;");


        idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Number>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Number>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("telephone"));
        emergencyContactColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("emergencyContact"));
        emergencyTelephoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("emergencyTelephone"));


        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(ageColumn);
        tableView.getColumns().add(genderColumn);
        tableView.getColumns().add(telephoneColumn);
        tableView.getColumns().add(emergencyContactColumn);
        tableView.getColumns().add(emergencyTelephoneColumn);


        //设置更改模式
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object + "";
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Integer.parseInt(string);
                } catch (Exception e) {
                    return -1;
                }
            }
        }));
        genderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emergencyContactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emergencyTelephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //绑定提交事件 当更改提交时，进行保存
        nameColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        ageColumn.setOnEditCommit(event -> event.getRowValue().setAge((Integer) event.getNewValue()));
        genderColumn.setOnEditCommit(event -> event.getRowValue().setGender(event.getNewValue()));
        telephoneColumn.setOnEditCommit(event -> event.getRowValue().setTelephone(event.getNewValue()));
        emergencyContactColumn.setOnEditCommit(event -> event.getRowValue().setEmergencyContact(event.getNewValue()));
        emergencyTelephoneColumn.setOnEditCommit(event -> event.getRowValue().setEmergencyTelephone(event.getNewValue()));

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //TODO 增强模糊查询，改为全字段查询
        //模糊查询功能
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String keyword = searchText.getText();

                if (keyword == null || keyword.isEmpty()) {
                    list.remove(0, list.size());
                    list.addAll(patientService.getAllPatients());
                } else {
                    list.remove(0, list.size());
                    list.addAll(patientService.fuzzyQueryPatients(keyword));
                }

                //tableView.refresh();
            }
        });


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/addPatient.fxml");
                Stage stage = new Stage();
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);


                TextField nameField = (TextField) anchorPane.getChildren().get(0);
                ComboBox yearBox = (ComboBox) anchorPane.getChildren().get(1);
                ComboBox monthBox = (ComboBox) anchorPane.getChildren().get(2);
                TextField genderField = (TextField) anchorPane.getChildren().get(3);
                TextField telephoneField = (TextField) anchorPane.getChildren().get(4);
                TextField emergencyContactField = (TextField) anchorPane.getChildren().get(5);
                TextField emergencyTelephoneField = (TextField) anchorPane.getChildren().get(6);

                Button submitButton = (Button) anchorPane.getChildren().get(13);
                Button cancelButton = (Button) anchorPane.getChildren().get(14);


                for (int i = 1920; i <= 2021; i++) {
                    yearBox.getItems().add(i);
                }

                for (int i = 1; i <= 12; i++) {
                    monthBox.getItems().add(i);
                }


                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        boolean flag = true;
                        String errMsg = "";

                        String name = nameField.getText();
                        String gender = genderField.getText();
                        Object yearObj = yearBox.getValue();
                        Object monthObj = monthBox.getValue();
                        String telephone = telephoneField.getText();
                        String emergencyContact = emergencyContactField.getText();
                        String emergencyTelephone = emergencyTelephoneField.getText();

                        if(name == null || "".equals(name)){
                            flag = false;
                            errMsg += "姓名不能为空\n";
                        }
                        if(!"男".equals(gender) && !"女".equals(gender)){
                            flag = false;
                            errMsg += "性别只能为\"男\"或\"女\"\n";
                        }
                        if(yearObj == null || monthObj == null){
                            flag = false;
                            errMsg += "您未选择出生年月\n";
                        }
                        if(!Pattern.matches("^([1]\\d{10}|([\\(（]?0[0-9]{2,3}[）\\)]?[-]?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)$", telephone)){
                            flag = false;
                            errMsg += "您的电话号码填写有误\n";
                        }
                        if(emergencyContact == null || "".equals(emergencyContact)){
                            flag = false;
                            errMsg += "紧急联系人姓名不能为空\n";
                        }
                        if(!Pattern.matches("^([1]\\d{10}|([\\(（]?0[0-9]{2,3}[）\\)]?[-]?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)$", emergencyTelephone)){
                            flag = false;
                            errMsg += "您的紧急联系电话号码填写有误\n";
                        }

                        if (flag) {
                            //如果添加成功，添加病患、进行展示并关闭窗口
                            Patient patient = new Patient();
                            patient.setName(name);
                            patient.setAge(2021 - (int)yearObj);
                            patient.setGender(gender);
                            patient.setTelephone(telephone);
                            patient.setEmergencyContact(emergencyContact);
                            patient.setEmergencyTelephone(emergencyTelephone);
                            patientService.addPatient(patient);
                            list.add(patient);
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

                stage.show();

            }
        });


        //TODO 删除提示框
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                List<Object> itemList = new ArrayList<>();
                itemList.addAll(selectedItems);

                for (Object o : itemList) {
                    Patient patient = (Patient) o;
                    patientService.deletePatientById(patient.getPid());
                    list.remove(patient);
                }
            }
        });


        List<Template> allTemplates = templateService.getAllTemplates();
        comboBox.getItems().addAll(allTemplates);


        evaluateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Object value = comboBox.getValue();
                Template template = (Template) value;


                //判断用户是否选择模板
                if (value == null) {
                    FxDialogUtils.showMessageDialog((Stage) root.getScene().getWindow(), "请选择测评模板", "");
                    return;
                }

                //判断模板是否为空
                List<Question> questionList = questionService.getQuestionsByTid(template.getTid());
                if (questionList.size() == 0) {
                    FxDialogUtils.showMessageDialog((Stage) root.getScene().getWindow(), "您选择的模板尚未添加问题", "");
                    return;
                }


                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                //判断用户是否选择一个病人
                if (selectedItems.size() != 1) {
                    FxDialogUtils.showMessageDialog((Stage) root.getScene().getWindow(), "您需要选择一名病人", "");
                    return;
                }

                Patient patient = (Patient) selectedItems.get(0);


                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/quiz.fxml");
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.show();


                Label index = (Label) anchorPane.getChildren().get(0);
                Label title = (Label) anchorPane.getChildren().get(1);
                Label choice1 = (Label) anchorPane.getChildren().get(2);
                Label choice2 = (Label) anchorPane.getChildren().get(3);
                Label choice3 = (Label) anchorPane.getChildren().get(4);
                Button button1 = (Button) anchorPane.getChildren().get(5);
                Button button2 = (Button) anchorPane.getChildren().get(6);
                Button button3 = (Button) anchorPane.getChildren().get(7);

                //为了在内部类使用
                AtomicReference<Integer> currentQuestionIndex = new AtomicReference<>(0);
                AtomicReference<Integer> totScore = new AtomicReference<>(0);

                loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3);

                EvaluationInfo evaluationInfo = new EvaluationInfo();
                evaluationInfo.setTid(template.getTid());
                evaluationInfo.setPid(patient.getPid());
                //TODO 设置建议
                evaluationInfo.setTime(DateUtils.format(DateUtils.getSysDate(), DateUtils.DATEFORMATSECOND));
                evaluationInfo.setSuggestion("");
                evaluationInfo.setEid(Status.currentEmployee.getEid());


                button1.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 5);
                    if (!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)) {
                        stage.close();
                        FxDialogUtils.showMessageDialog((Stage) root.getScene().getWindow(), "您已完成测评", "");


                        evaluationInfoService.addEvaluationInfo(evaluationInfo);

                    }
                });
                button2.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 3);
                    if (!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)) {
                        stage.close();
                        System.out.println(totScore);
                    }
                });
                button3.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 1);
                    if (!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)) {
                        stage.close();
                        System.out.println(totScore);
                    }
                });


            }
        });


    }


    private boolean loadQuestion(List<Question> list, int questionIndex, Label index, Label title, Label choice1, Label choice2, Label choice3) {
        if (questionIndex >= list.size()) {
            return false;
        }
        Question question = list.get(questionIndex);
        index.setText("第" + (questionIndex + 1) + "题");
        title.setText(question.getTitle());
        choice1.setText("A. " + question.getChoice1());
        choice2.setText("B. " + question.getChoice2());
        choice3.setText("C. " + question.getChoice3());
        return true;
    }


}
