package cn.edu.neu.util;

import cn.edu.neu.commom.Status;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * 提供与JavaFX相关的方法
 */
public class FxLoadNodeUtils {

    /**
     * 根据fxml文件获得AnchorPane对象。注意: fxml文件的根结点应为AnchorPane。
     * @param path fxml文件的相对路径
     * @return AnchorPane对象
     */
    public static AnchorPane loadAnchor(String path){
        return (AnchorPane) loadNode(path);
    }

    /**
     * 获得root.xml中的BorderPane对象
     * @return root.xml中的BorderPane对象
     */
    public static BorderPane loadRoot(){
        return (BorderPane) loadNode("fxml/common/root.fxml");
    }

    /**
     * 根据fxml文件获得根结点对象。注意: fxml文件的根结点应为Node的子类。
     * @param path fxml文件的相对路径
     * @return AnchorPane对象
     */
    public static Node loadNode(String path){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(FxLoadNodeUtils.class.getClassLoader().getResource(path));
            return fxmlLoader.load();
        } catch (Exception e) {
            System.err.println("无法成功解析fxml文件，路径为: " + path);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用户提供两个分类，该方法会根据用户的分类，将left。top、center版面进行添加，并将添加好的BorderPane对象进行返回。
     * @param category1 一级分类，例如：总览、用户管理、建筑管理、评估管理
     * @param category2 二级分类：例如用户管理下的患者管理、床位管理、设备管理
     * @return BorderPane根结点
     */
    public static BorderPane getTotalPane(String category1, String category2){
        BorderPane borderPane = loadRoot();

        AnchorPane left = loadAnchor("fxml/common/left.fxml");
        AnchorPane top = loadAnchor("fxml/common/top.fxml");
        AnchorPane center = loadAnchor("fxml/center/" + category1 + "/" + category2 + ".fxml");

        borderPane.setTop(top);
        borderPane.setLeft(left);
        borderPane.setCenter(center);

        return borderPane;
    }

    /**
     * 更换中心版面，并更新category
     */
    public static void changeCenter(BorderPane borderPane, String category1, String category2){
        Status.overview.setStyle("-fx-text-fill: black");
        Status.structure.setStyle("-fx-text-fill: black");
        Status.evaluation.setStyle("-fx-text-fill: black");
        Status.user.setStyle("-fx-text-fill: black");

        Status.lbl1.setStyle("-fx-text-fill: black");
        Status.lbl2.setStyle("-fx-text-fill: black");
        Status.lbl3.setStyle("-fx-text-fill: black");
        Status.lbl4.setStyle("-fx-text-fill: black");
        Status.lbl5.setStyle("-fx-text-fill: black");

        if("overview".equals(category1)){
            Status.overview.setStyle("-fx-text-fill: #2585a6");
        }else if("evaluation".equals(category1)){
            Status.evaluation.setStyle("-fx-text-fill: #2585a6");
        }else if("structure".equals(category1)){
            Status.structure.setStyle("-fx-text-fill: #2585a6");
        }else if("user".equals(category1)){
            Status.user.setStyle("-fx-text-fill: #2585a6");
        }


        if("hospital".equals(category2) || "patient".equals(category2) || "template".equals(category2) || "building".equals(category2)){
            Status.lbl1.setStyle("-fx-text-fill: #2585a6");
        }else if("bed".equals(category2) || "question".equals(category2)){
            Status.lbl2.setStyle("-fx-text-fill: #2585a6");
        }else if("rareEquipment".equals(category2) || "evaluation".equals(category2)){
            Status.lbl3.setStyle("-fx-text-fill: #2585a6");
        }

        Status.category1 = category1;
        Status.category2 = category2;

        AnchorPane center = loadAnchor("fxml/center/" + category1 + "/" + category2 + ".fxml");
        borderPane.setCenter(center);
    }


}
