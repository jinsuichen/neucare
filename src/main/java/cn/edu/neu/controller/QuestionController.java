package cn.edu.neu.controller;

import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.service.QuestionService;
import cn.edu.neu.service.impl.QuestionServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class QuestionController {
    @FXML
    private TableView tableView;
    @FXML
    private Button addButton;
    @FXML
    private Button detailButton;
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

        //设置更改模式
        tableView.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        choice1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        choice2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        choice3Column.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());


    }

}
