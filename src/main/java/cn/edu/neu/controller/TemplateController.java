package cn.edu.neu.controller;

import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.PatientService;
import cn.edu.neu.service.TemplateService;
import cn.edu.neu.service.impl.PatientServiceImpl;
import cn.edu.neu.service.impl.TemplateServiceImpl;
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
import javafx.util.StringConverter;

import javax.xml.transform.Templates;
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


    @FXML
    public void initialize(){

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

                for(Object o : itemList){
                    Template template = (Template) o;
                    templateService.deleteTemplateById(template.getTid());
                    list.remove(template);
                }
            }
        });



    }




}
