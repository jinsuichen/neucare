package cn.edu.neu.controller;

import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.QuestionService;
import cn.edu.neu.service.TemplateService;
import cn.edu.neu.service.impl.QuestionServiceImpl;
import cn.edu.neu.service.impl.TemplateServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

public class TemplateController {
    @FXML
    public TableView tableView;
    @FXML
    public Button addButton;
    @FXML
    public Button detailButton;
    @FXML
    public Button deleteButton;



    public TemplateController() {
    }

    private ObservableList<Template> list = FXCollections.observableArrayList();

    private TemplateService templateService = new TemplateServiceImpl();
    private QuestionService questionService = new QuestionServiceImpl();


    @FXML
    public void initialize() {

        //为可观察列表添加数据
        list.addAll(templateService.getAllTemplates());
        //将可观察列表与表格绑定
        tableView.setItems(list);


        TableColumn<Template, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Template, String> nameColumn = new TableColumn<>("题目");
        TableColumn<Template, String> typeColumn = new TableColumn<>("类型");


        idColumn.setCellValueFactory(new PropertyValueFactory<Template, Number>("tid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Template, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Template, String>("type"));


        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(typeColumn);


        //设置更改模式
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //绑定提交事件 当更改提交时，进行保存
        nameColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));
        typeColumn.setOnEditCommit(event -> event.getRowValue().setType(event.getNewValue()));

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                List<Object> itemList = new ArrayList<>();
                itemList.addAll(selectedItems);

                for (Object o : itemList) {
                    Template template = (Template) o;
                    templateService.deleteTemplateById(template.getTid());
                    list.remove(template);
                }
            }
        });


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/addTemplate.fxml");
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);


                stage.show();


                TextField nameTextFiled = (TextField) anchorPane.getChildren().get(2);
                TextField typeTextFiled = (TextField) anchorPane.getChildren().get(3);
                Button confirmButton = (Button) anchorPane.getChildren().get(4);
                Button cancelButton = (Button) anchorPane.getChildren().get(5);


                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });

                confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String name = nameTextFiled.getText();
                        String type = typeTextFiled.getText();

                        Template template = new Template();
                        template.setName(name);
                        template.setType(type);

                        if (templateService.addTemplate(template)) {
                            list.add(template);
                            stage.close();
                        }

                        stage.close();
                    }
                });


            }
        });


        detailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                if (selectedItems.size() != 1) {
                    return;
                }

                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/templateDetail.fxml");
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);

                //获取子控件
                Label label = (Label) anchorPane.getChildren().get(0);
                TableView questionTable = (TableView) anchorPane.getChildren().get(1);
                Button addQuestionButton = (Button) anchorPane.getChildren().get(2);
                Button delQuestionButton = (Button) anchorPane.getChildren().get(3);
                stage.show();

                //获取模板信息
                Template selectedTemplate = (Template)selectedItems.get(0);

                //为label添加模板名称
                label.setText("当前: " + selectedTemplate.getName());

                //在问题表格中展示问题数据
                List<Question> questionList = questionService.getQuestionsByTid(selectedTemplate.getTid());
                ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
                questionObservableList.addAll(questionList);
                questionTable.setItems(questionObservableList);

                TableColumn<Question, Number> idColumn = new TableColumn<>("ID");
                TableColumn<Question, String> titleColumn = new TableColumn<>("标题");
                TableColumn<Question, String> choice1Column = new TableColumn<>("选项1");
                TableColumn<Question, String> choice2Column = new TableColumn<>("选项2");
                TableColumn<Question, String> choice3Column = new TableColumn<>("选项3");
                TableColumn<Question, String> typeColumn = new TableColumn<>("类型");

                idColumn.setCellValueFactory(new PropertyValueFactory<Question, Number>("qid"));
                titleColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("title"));
                choice1Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice1"));
                choice2Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice2"));
                choice3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice3"));
                typeColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("type"));

                questionTable.getColumns().add(idColumn);
                questionTable.getColumns().add(titleColumn);
                questionTable.getColumns().add(choice1Column);
                questionTable.getColumns().add(choice2Column);
                questionTable.getColumns().add(choice3Column);
                questionTable.getColumns().add(typeColumn);

                //设置可多选
                questionTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


                //添加问题
                addQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        AnchorPane ap = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/bindQuestion.fxml");
                        Scene scene = new Scene(ap);
                        Stage bindQuestionStage = new Stage();
                        bindQuestionStage.setScene(scene);
                        bindQuestionStage.setResizable(false);
                        bindQuestionStage.initModality(Modality.APPLICATION_MODAL);

                        //获取子控件
                        TableView questionTable = (TableView) ap.getChildren().get(1);
                        Button confirmButton = (Button) ap.getChildren().get(2);
                        Button cancelButton = (Button) ap.getChildren().get(3);
                        bindQuestionStage.show();

                        //在问题表格中展示问题数据
                        List<Question> questionList = questionService.getQuestionsWithNoTemplate();
                        ObservableList<Question> bindableObservableList = FXCollections.observableArrayList();
                        bindableObservableList.addAll(questionList);
                        questionTable.setItems(bindableObservableList);

                        TableColumn<Question, Number> idColumn = new TableColumn<>("ID");
                        TableColumn<Question, String> titleColumn = new TableColumn<>("标题");
                        TableColumn<Question, String> choice1Column = new TableColumn<>("选项1");
                        TableColumn<Question, String> choice2Column = new TableColumn<>("选项2");
                        TableColumn<Question, String> choice3Column = new TableColumn<>("选项3");
                        TableColumn<Question, String> typeColumn = new TableColumn<>("类型");

                        idColumn.setCellValueFactory(new PropertyValueFactory<Question, Number>("qid"));
                        titleColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("title"));
                        choice1Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice1"));
                        choice2Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice2"));
                        choice3Column.setCellValueFactory(new PropertyValueFactory<Question, String>("choice3"));
                        typeColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("type"));

                        questionTable.getColumns().add(idColumn);
                        questionTable.getColumns().add(titleColumn);
                        questionTable.getColumns().add(choice1Column);
                        questionTable.getColumns().add(choice2Column);
                        questionTable.getColumns().add(choice3Column);
                        questionTable.getColumns().add(typeColumn);

                        //设置可多选
                        questionTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


                        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                ObservableList selectedItems = questionTable.getSelectionModel().getSelectedItems();


                                List<Object> itemList = new ArrayList<>();
                                itemList.addAll(selectedItems);

                                for (Object o : itemList) {
                                    Question question = (Question) o;
                                    templateService.bindQuestion(selectedTemplate.getTid(), question.getQid());
                                    questionObservableList.add(question);
                                }

                                //stage.close();
                                bindQuestionStage.close();
                            }
                        });

                        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                bindQuestionStage.close();
                            }
                        });
                    }
                });

                //删除问题
                delQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ObservableList selectedItems = questionTable.getSelectionModel().getSelectedItems();

                        List<Object> itemList = new ArrayList<>();
                        itemList.addAll(selectedItems);

                        for (Object o : itemList) {
                            Question question = (Question) o;
                            //FIXME -1
                            templateService.removeQuestion(-1, question.getQid());
                            questionObservableList.remove(question);
                        }
                    }
                });


            }
        });


    }


}
