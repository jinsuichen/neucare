package cn.edu.neu.controller;

import cn.edu.neu.pojo.Question;
import cn.edu.neu.service.QuestionService;
import cn.edu.neu.service.impl.QuestionServiceImpl;
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

public class QuestionController {

    @FXML
    private TableView tableView;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;


    public QuestionController() {
    }

    private ObservableList<Question> list = FXCollections.observableArrayList();

    private final QuestionService questionService = new QuestionServiceImpl();

    @FXML
    public void initialize() {

        //为可观察列表添加数据
        list.addAll(questionService.getAllQuestions());
        //将可观察列表与表格绑定
        tableView.setItems(list);


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

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(titleColumn);
        tableView.getColumns().add(choice1Column);
        tableView.getColumns().add(choice2Column);
        tableView.getColumns().add(choice3Column);
        tableView.getColumns().add(typeColumn);

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //设置更改模式
        tableView.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        choice1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        choice2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        choice3Column.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //绑定提交事件 当更改提交时，进行保存
        titleColumn.setOnEditCommit(event -> event.getRowValue().setTitle(event.getNewValue()));
        choice1Column.setOnEditCommit(event -> event.getRowValue().setChoice1(event.getNewValue()));
        choice2Column.setOnEditCommit(event -> event.getRowValue().setChoice2(event.getNewValue()));
        choice3Column.setOnEditCommit(event -> event.getRowValue().setChoice3(event.getNewValue()));
        typeColumn.setOnEditCommit(event -> event.getRowValue().setType(event.getNewValue()));

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                AnchorPane anchorPane = (AnchorPane) FxLoadNodeUtils.loadNode("fxml/other/addQuestion.fxml");
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                stage.setResizable(false);


                TextField titleTextField = (TextField) anchorPane.getChildren().get(4);
                TextField choice1TextField = (TextField) anchorPane.getChildren().get(5);
                TextField choice2TextField = (TextField) anchorPane.getChildren().get(6);
                TextField choice3TextField = (TextField) anchorPane.getChildren().get(7);
                Button confirmButton = (Button) anchorPane.getChildren().get(8);
                Button cancelButton = (Button) anchorPane.getChildren().get(9);



                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });

                confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Question question = new Question();
                        question.setTitle(titleTextField.getText());
                        question.setChoice1(choice1TextField.getText());
                        question.setChoice2(choice2TextField.getText());
                        question.setChoice3(choice3TextField.getText());
                        if(questionService.addQuestion(question)){
                            //如果添加成功，进行数据展示并关闭窗口
                            list.add(question);
                            stage.close();
                        }
                    }
                });

            }
        });


        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedItems = tableView.getSelectionModel().getSelectedItems();

                List<Object> itemList = new ArrayList<>();
                itemList.addAll(selectedItems);

                for(Object o : itemList){
                    Question question = (Question)o;
                    questionService.deletePatientById(question.getQid());
                    list.remove(question);

                }

            }
        });




    }

}
