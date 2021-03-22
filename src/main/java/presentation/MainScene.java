package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.panel.BottomMenuPanel;
import presentation.panel.LeftMenuPanel;
import presentation.panel.MobilePanel;

public class MainScene extends Scene {

    @FXML
    private BorderPane rootContainer;

    @FXML
    private MobilePanel mobilePanel;

    @FXML
    private LeftMenuPanel leftMenu;

    @FXML
    private BottomMenuPanel bottomMenu;

    @FXML
    private ImageView rotateButton;

    @FXML
    private ImageView volumeButton;

    @FXML
    private ImageView muteButton;

    @FXML
    private ImageView lockButton;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView homeButton;

    public MainScene(){
        super(new Pane(), 600, 600);
    }

    @FXML
    public void initialize(){
        leftMenu.setLockButton(lockButton);
        leftMenu.setMuteButton(muteButton);
        leftMenu.setRotateButton(rotateButton);
        leftMenu.setVolumeButton(volumeButton);
        bottomMenu.setBackButton(backButton);
        bottomMenu.setHomeButton(homeButton);
    }

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
    }
}
