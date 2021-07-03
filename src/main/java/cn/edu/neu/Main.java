package cn.edu.neu;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello Javafx");

        primaryStage.setTitle("Hello JavaFx");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
