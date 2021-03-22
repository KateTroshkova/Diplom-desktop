package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CustomApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Troshkova E.L. AVT-716");
        URL url = new File("src/main/resources/design.fxml").toURI().toURL();
        BorderPane root = FXMLLoader.load(url);
        MainScene scene = new MainScene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}