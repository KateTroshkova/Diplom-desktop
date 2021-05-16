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

public class CustomApplication extends Application implements SceneChangeListener{

    private MainScene main;
    private HotkeyScene hotkey;
    private Stage primaryStage;

    private static final String title="Troshkova E.L. AVT-716";
    private static final String icPath="ic_launcher.png";
    private static final String homePath="home.fxml";
    private static final String hotkeyPath="dialog_hotkey.fxml";

    private static final int mainWidth=800;
    private static final int mainHeight=1000;
    private static final int hotkeyWidth=600;
    private static final int hotkeyHeight=600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image(icPath));
        //URL homeUrl = new File(homePath).toURI().toURL();
        //URL hotkeyUrl = new File(hotkeyPath).toURI().toURL();
        BorderPane homeRoot = FXMLLoader.load(getClass().getClassLoader().getResource(homePath));
        VBox hotkeyRoot = FXMLLoader.load(getClass().getClassLoader().getResource(hotkeyPath));
        main = new MainScene(homeRoot, mainWidth, mainHeight);
        hotkey = new HotkeyScene(hotkeyRoot, hotkeyWidth, hotkeyHeight);
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