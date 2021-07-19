package cn.edu.neu.controller;

import cn.edu.neu.pojo.*;
import cn.edu.neu.service.BedService;
import cn.edu.neu.service.FloorService;
import cn.edu.neu.service.StructureService;
import cn.edu.neu.service.WardService;
import cn.edu.neu.service.impl.BedServiceImpl;
import cn.edu.neu.service.impl.FloorServiceImpl;
import cn.edu.neu.service.impl.StructureServiceImpl;
import cn.edu.neu.service.impl.WardServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BuildingController {
    @FXML
    private TextField textField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private TreeView<Object> treeView;


    public BuildingController() {
    }

    StructureService structureService = new StructureServiceImpl();
    FloorService floorService = new FloorServiceImpl();
    WardService wardService = new WardServiceImpl();
    BedService bedService = new BedServiceImpl();

    @FXML
    public void initialize() {

        //TODO 将稀有设备添加进来
        TreeItem<Object> root = new TreeItem<>("医院");
        treeView.setRoot(root);
        buildTree(root);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TreeItem<Object> selectedItem = treeView.getSelectionModel().getSelectedItem();
                Object obj = selectedItem.getValue();

                //FIXME 将123合并
                if (obj instanceof Structure) {
                    if (selectedItem.getChildren().size() == 0) {
                        selectedItem.getParent().getChildren().remove(selectedItem);
                        ((Structure) obj).setDeleted(true);
                    }
                } else if (obj instanceof Floor) {
                    if (selectedItem.getChildren().size() == 0) {
                        selectedItem.getParent().getChildren().remove(selectedItem);
                        ((Floor) obj).setDeleted(true);
                    }
                } else if (obj instanceof Ward) {
                    if (selectedItem.getChildren().size() == 0) {
                        selectedItem.getParent().getChildren().remove(selectedItem);
                        ((Ward) obj).setDeleted(true);
                    }
                } else if (obj instanceof Bed) {
                    selectedItem.getParent().getChildren().remove(selectedItem);
                    ((Bed) obj).setDeleted(true);
                }


            }

        });


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TreeItem<Object> selectedItem = treeView.getSelectionModel().getSelectedItem();
                Object obj = selectedItem.getValue();


                //TODO 提示框
                if(obj instanceof Bed){
                    return;
                }

                //根据选择的建筑的不同，进行不同的操作
                if(obj instanceof String){
                    Structure structure = new Structure();
                    structure.setName(textField.getText());
                    structureService.addStructure(structure);
                    selectedItem.getChildren().add(new TreeItem<>(structure));
                }else if (obj instanceof Structure) {
                    Floor floor = new Floor();
                    floor.setSid(((Structure)selectedItem.getValue()).getSid());
                    floor.setName(textField.getText());
                    floorService.addFloor(floor);
                    selectedItem.getChildren().add(new TreeItem<>(floor));
                } else if (obj instanceof Floor) {
                    Ward ward = new Ward();
                    ward.setFid(((Floor)selectedItem.getValue()).getFid());
                    ward.setName(textField.getText());
                    wardService.addWard(ward);
                    selectedItem.getChildren().add(new TreeItem<>(ward));

                } else if (obj instanceof Ward) {
                    Bed bed = new Bed();
                    bed.setWid(((Ward)selectedItem.getValue()).getWid());
                    bed.setName(textField.getText());
                    bedService.addBed(bed);
                    selectedItem.getChildren().add(new TreeItem<>(bed));
                }

                textField.setText("");
            }
        });


    }

    /**
     * 宽度优先搜索查找TreeView中指定的对象
     *
     * @param root TreeView的根结点
     * @param obj  待查找的对象
     * @return 找到的结点
     */
    private TreeItem<Object> bfs(TreeItem<Object> root, Object obj) {
        Queue<TreeItem<Object>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeItem<Object> item = queue.remove();
            if (item.getValue() == obj) {
                return item;
            } else {
                queue.addAll(item.getChildren());
            }
        }
        return null;
    }


    /**
     * 建树
     *
     * @param root 根结点
     */
    private void buildTree(TreeItem<Object> root) {

        List<Structure> structures = structureService.getAllStructures();
        for (Structure s : structures) {
            TreeItem<Object> structureItem = new TreeItem<>(s);
            root.getChildren().add(structureItem);
            List<Floor> floors = floorService.getFloorsBySid(s.getSid());
            for (Floor f : floors) {
                TreeItem<Object> floorItem = new TreeItem<>(f);
                structureItem.getChildren().add(floorItem);
                List<Ward> wards = wardService.getWardsByFid(f.getFid());
                for (Ward w : wards) {
                    TreeItem<Object> wardItem = new TreeItem<>(w);
                    floorItem.getChildren().add(wardItem);
                    List<Bed> beds = bedService.getBedsByWid(w.getWid());
                    for (Bed b : beds) {
                        TreeItem<Object> bedItem = new TreeItem<>(b);
                        wardItem.getChildren().add(bedItem);
                    }
                }
            }
        }
    }

}
