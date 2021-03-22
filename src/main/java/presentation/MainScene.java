package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.panel.MobilePanel;

public class MainScene extends Scene {

    @FXML
    private BorderPane rootContainer;

    @FXML
    private MobilePanel mobilePanel;

    public MainScene(){
        super(new Pane(), 1200, 700);
    }

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
    }
}
