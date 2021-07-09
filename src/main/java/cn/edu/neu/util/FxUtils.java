package cn.edu.neu.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class FxUtils {

    /**
     * 根据fxml文件获得AnchorPane对象。注意: fxml文件的根结点应为AnchorPane。
     * @param path fxml文件的相对路径
     * @return AnchorPane对象
     */
    public static AnchorPane loadItem(String path){
        return (AnchorPane) loadNode(path);
    }

    /**
     * 获得root.xml中的BorderPane对象
     * @return root.xml中的BorderPane对象
     */
    public static BorderPane loadRoot(){
        return (BorderPane) loadNode("fxml/root.fxml");
    }

    /**
     * 根据fxml文件获得根结点对象。注意: fxml文件的根结点应为Node的子类。
     * @param path fxml文件的相对路径
     * @return AnchorPane对象
     */
    public static Node loadNode(String path){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(FxUtils.class.getClassLoader().getResource(path));
            return fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
