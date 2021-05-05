package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class CustomApplication extends Application implements SceneChangeListener{

    private MainScene main;
    private HotkeyScene hotkey;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Troshkova E.L. AVT-716");
        primaryStage.getIcons().add(new Image("ic_launcher.png"));
        URL homeUrl = new File("src/main/resources/home.fxml").toURI().toURL();
        URL hotkeyUrl = new File("src/main/resources/dialog_hotkey.fxml").toURI().toURL();
        BorderPane homeRoot = FXMLLoader.load(homeUrl);
        VBox hotkeyRoot = FXMLLoader.load(hotkeyUrl);
        main = new MainScene(homeRoot, 720, 720);
        hotkey = new HotkeyScene(hotkeyRoot, 600, 600);
        primaryStage.setScene(main);
        primaryStage.show();
        main.setSceneChangeListener(this);
        hotkey.setSceneChangeListener(this);
    }

    @Override
    public void onOpenDialog() {
        primaryStage.setScene(hotkey);
        hotkey.prepareData();
    }

    @Override
    public void onCloseDialog() {
        primaryStage.setScene(main);
    }
}