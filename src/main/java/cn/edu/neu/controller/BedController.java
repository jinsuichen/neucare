package cn.edu.neu.controller;


import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.*;
import cn.edu.neu.service.*;
import cn.edu.neu.service.impl.*;
import cn.edu.neu.util.FxUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BedController {

    @FXML
    public Button checkinButton;
    @FXML
    private TableView tableView;
    @FXML
    private Button checkoutButton;
    @FXML
    private ComboBox structureBox;
    @FXML
    private ComboBox floorBox;
    @FXML
    private ComboBox wardBox;
    @FXML
    private Button swapButton;

    private final StructureService structureService = new StructureServiceImpl();
    private final FloorService floorService = new FloorServiceImpl();
    private final WardService wardService = new WardServiceImpl();
    private final BedService bedService = new BedServiceImpl();
    private final PatientService patientService = new PatientServiceImpl();

    //表格数据源
    private ObservableList<Bed> list = null;

    public BedController() {
    }

    private void query() {
        Structure structure = (Structure) structureBox.getValue();
        Floor floor = (Floor) floorBox.getValue();
        Ward ward = (Ward) wardBox.getValue();
        list.remove(0, list.size());

        if (structure == null) {
            list.addAll(bedService.getAllBeds());
        } else if (floor == null) {
            list.addAll(bedService.getBedsBySid(structure.getSid()));
        } else if (ward == null) {
            list.addAll(bedService.getBedsByFid(floor.getFid()));
        } else {
            list.addAll(bedService.getBedsByWid(ward.getWid()));
        }
    }

    @FXML
    public void initialize() {


        //创建可观察列表作为表格数据源
        list = FXCollections.observableArrayList();

        list.addAll(bedService.getAllBeds());

        //将可观察列表与表格绑定
        tableView.setItems(list);

        //设置可多选
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //生成Column
        TableColumn<Bed, Number> idColumn = new TableColumn<>("ID");
        TableColumn<Bed, String> locationColumn = new TableColumn<>("位置");
        TableColumn<Bed, String> startTimeColumn = new TableColumn<>("开始时间");
        TableColumn<Bed, String> endTimeColumn = new TableColumn<>("结束时间");
        TableColumn<Bed, String> statusColumn = new TableColumn<>("状态");
        TableColumn<Bed, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Bed, String> genderColumn = new TableColumn<>("性别");
        TableColumn<Bed, String> ageColumn = new TableColumn<>("年龄");

        //添加表格信息
        idColumn.setCellValueFactory(new PropertyValueFactory<Bed, Number>("bid"));
        locationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bed, String> param) {
                Bed bed = (Bed) param.getValue();
                int wid = bed.getWid();
                Ward ward = wardService.getWardByWid(wid);
                int fid = ward.getFid();
                Floor floor = floorService.getFloorByFid(fid);
                int sid = floor.getSid();
                Structure structure = structureService.getStructureBySid(sid);

                String str = structure.getName() + " -> " + floor.getHeight() + " -> " + ward.getName() + " -> " + bed.getName();
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });

        //TODO 添加起止日期

        statusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bed, String> param) {
                Bed bed = (Bed) param.getValue();
                String str;
                if (bed.getPid() == null) {
                    str = "空闲";
                } else {
                    str = "使用中";
                }
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bed, String> param) {
                Bed bed = (Bed) param.getValue();
                String str;
                if (bed.getPid() == null) {
                    str = "";
                } else {
                    Patient patient = patientService.getPatientByBid(bed.getBid());
                    str = patient.getName();
                }
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });

        ageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bed, String> param) {
                Bed bed = (Bed) param.getValue();
                String str;
                if (bed.getPid() == null) {
                    str = "";
                } else {
                    Patient patient = patientService.getPatientByBid(bed.getBid());
                    str = patient.getAge() + "";
                }

                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });

        genderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bed, String> param) {
                Bed bed = (Bed) param.getValue();
                String str;
                if (bed.getPid() == null) {
                    str = "";
                } else {
                    Patient patient = patientService.getPatientByBid(bed.getBid());
                    str = patient.getGender();
                }
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(str);
                return simpleStringProperty;
            }
        });


        /*emergencyTelephoneColumn.setCellValueFactory(param -> {
            SimpleStringProperty emergencyTelephone = new SimpleStringProperty(param.getValue().getEmergencyTelephone());
            return emergencyTelephone;
        });*/

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(locationColumn);
        tableView.getColumns().add(startTimeColumn);
        tableView.getColumns().add(endTimeColumn);
        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(genderColumn);
        tableView.getColumns().add(ageColumn);


        //为structure添加下拉列表
        structureBox.getItems().addAll(structureService.getAllStructures());


        structureBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int sid = ((Structure) structureBox.getValue()).getSid();
                floorBox.getItems().remove(0, floorBox.getItems().size());
                wardBox.getItems().remove(0, wardBox.getItems().size());
                floorBox.getItems().addAll(floorService.getFloorsBySid(sid));
                query();
            }
        });

        floorBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int fid = ((Floor) floorBox.getValue()).getFid();
                wardBox.getItems().remove(0, wardBox.getItems().size());
                wardBox.getItems().addAll(wardService.getWardsByFid(fid));
                query();
            }
        });

        wardBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                query();

            }
        });

        swapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ObservableList list = tableView.getSelectionModel().getSelectedItems();

                if (list.size() == 2) {
                    Bed bed1 = (Bed) list.get(0);
                    Bed bed2 = (Bed) list.get(1);
                    bedService.swap(bed1, bed2);
                    tableView.refresh();
                }

            }
        });

        checkinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ObservableList bedList = tableView.getSelectionModel().getSelectedItems();


                if (bedList.size() != 1) {
                    //TODO 弹出提示框，提示选择有误
                } else {
                    Bed bed = (Bed) bedList.get(0);

                    Stage stage = new Stage();
                    AnchorPane anchorPane = (AnchorPane) FxUtils.loadNode("fxml/other/bedCheckin.fxml");
                    Scene scene = new Scene(anchorPane);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);

                    TableView patientTable = (TableView) anchorPane.getChildren().get(0);
                    Button submit = (Button) anchorPane.getChildren().get(1);
                    Button cancel = (Button) anchorPane.getChildren().get(2);

                    //创建可观察列表
                    ObservableList<Patient> list = FXCollections.observableArrayList();
                    //为可观察列表添加数据
                    list.addAll(patientService.getAllPatientsWithNoBed());
                    //将可观察列表与表格绑定
                    patientTable.setItems(list);


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

                    patientTable.getColumns().add(idColumn);
                    patientTable.getColumns().add(nameColumn);
                    patientTable.getColumns().add(ageColumn);
                    patientTable.getColumns().add(genderColumn);
                    patientTable.getColumns().add(telephoneColumn);
                    patientTable.getColumns().add(emergencyContactColumn);
                    patientTable.getColumns().add(emergencyTelephoneColumn);

                    submit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                Patient patient = (Patient) patientTable.getSelectionModel().getSelectedItem();
                                Integer pid = patient.getPid();
                                bedService.checkin(bed, patient);
                                stage.close();
                                tableView.refresh();

                            } catch (Exception e) {
                                System.err.println("选择失败");
                                //TODO 弹窗
                            }
                        }
                    });

                    cancel.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                        }
                    });

                    stage.show();
                }


            }
        });

        checkoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList list = tableView.getSelectionModel().getSelectedItems();

                for (Object obj : list) {
                    Bed bed = (Bed) obj;
                    bedService.checkOut(bed);
                }

                tableView.refresh();
            }
        });


    }
}
