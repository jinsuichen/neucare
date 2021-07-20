package cn.edu.neu;

import cn.edu.neu.po.DataBase;
import cn.edu.neu.util.FileUtils;
import cn.edu.neu.util.FxLoadNodeUtils;
import cn.edu.neu.util.JsonUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //FIXME 造假数据测试使用 记得删除
        //CreateDataUtils.main(null);

        AnchorPane root = FxLoadNodeUtils.loadAnchor("fxml/other/login.fxml");

        //设置背景图片
        BackgroundImage backgroundImage= new BackgroundImage(new Image("img/login.jpg",1280,720,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        primaryStage.setTitle("东软颐养中心-登录");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("css/login.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.show();
        primaryStage.setResizable(false);

    }


    @Override
    public void stop() throws Exception {


        FileUtils.write(JsonUtils.serialize(DataBase.adminData), "data/admins.json");
        FileUtils.write(JsonUtils.serialize(DataBase.patientData), "data/patients.json");
        FileUtils.write(JsonUtils.serialize(DataBase.bedData), "data/beds.json");
        FileUtils.write(JsonUtils.serialize(DataBase.employeeData), "data/employees.json");
        FileUtils.write(JsonUtils.serialize(DataBase.floorData), "data/floors.json");
        FileUtils.write(JsonUtils.serialize(DataBase.questionData), "data/questions.json");
        FileUtils.write(JsonUtils.serialize(DataBase.templateData), "data/templates.json");
        //FileUtils.write(JsonUtils.serialize(DataBase.rareEquipmentData), "data/rareEquipments.json");
        FileUtils.write(JsonUtils.serialize(DataBase.structureData), "data/structures.json");
        FileUtils.write(JsonUtils.serialize(DataBase.evaluationInfoData), "data/evaluationInfos.json");

        //FIXME 这句话可能没有用
        System.exit(0);



    }
}
