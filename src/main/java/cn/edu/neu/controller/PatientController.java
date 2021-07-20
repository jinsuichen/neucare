package cn.edu.neu.controller;

import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.QuestionService;
import cn.edu.neu.service.TemplateService;
import cn.edu.neu.service.impl.PatientServiceImpl;
import cn.edu.neu.service.impl.QuestionServiceImpl;
import cn.edu.neu.service.impl.TemplateServiceImpl;
import cn.edu.neu.util.FxUtils;
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

public class PatientController {
    @FXML
    private ComboBox comboBox;
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
                AnchorPane anchorPane = (AnchorPane) FxUtils.loadNode("fxml/other/addPatient.fxml");
                Stage stage = new Stage();
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);

                //FIXME for test
                //System.out.println(anchorPane.getChildren());
                //anchorPane.getChildren().forEach(System.out::println);

                TextField nameField = (TextField) anchorPane.getChildren().get(0);
                TextField birthdayField = (TextField) anchorPane.getChildren().get(1);
                TextField genderField = (TextField) anchorPane.getChildren().get(2);
                TextField telephoneField = (TextField) anchorPane.getChildren().get(3);
                TextField emergencyContactField = (TextField) anchorPane.getChildren().get(4);
                TextField emergencyTelephoneField = (TextField) anchorPane.getChildren().get(5);

                Button submitButton = (Button) anchorPane.getChildren().get(12);
                Button cancelButton = (Button) anchorPane.getChildren().get(13);


                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String errMsg = "";


                        Patient patient = new Patient();
                        patient.setName(nameField.getText());
                        //TODO 根据生日生成年龄
                        patient.setAge(16);
                        patient.setGender(genderField.getText());
                        patient.setTelephone(telephoneField.getText());
                        patient.setEmergencyContact(emergencyContactField.getText());
                        patient.setEmergencyTelephone(emergencyTelephoneField.getText());

                        if (patientService.addPatient(patient)) {
                            //如果添加成功，进行数据展示并关闭窗口
                            list.add(patient);
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
                    //TODO errmsg
                    return;
                }

                //判断模板是否为空
                List<Question> questionList = questionService.getQuestionsByTid(template.getTid());
                if (questionList.size() == 0) {
                    //TODO errmsg
                    return;
                }


                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                //判断用户是否选择一个病人
                if (selectedItems.size() != 1) {
                    //TODO errmsg
                    return;
                }

                Patient patient = (Patient) selectedItems.get(0);


                AnchorPane anchorPane = (AnchorPane) FxUtils.loadNode("fxml/other/quiz.fxml");
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

                loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index,title,choice1,choice2,choice3);

                button1.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 5);
                    if(!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)){
                        stage.close();
                        //TODO 弹出 测评完成
                        System.out.println(totScore);
                    }
                });
                button2.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 3);
                    if(!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)){
                        stage.close();
                        System.out.println(totScore);
                    }
                });
                button3.setOnAction(event1 -> {
                    totScore.updateAndGet(v -> v + 1);
                    if(!loadQuestion(questionList, currentQuestionIndex.getAndSet(currentQuestionIndex.get() + 1), index, title, choice1, choice2, choice3)){
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
        choice1.setText(question.getChoice1());
        choice2.setText(question.getChoice2());
        choice3.setText(question.getChoice3());
        return true;
    }


}
