package cn.edu.neu.view;

import cn.edu.neu.util.FxUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = FxUtils.loadAnchor("fxml/other/login.fxml");

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
}
