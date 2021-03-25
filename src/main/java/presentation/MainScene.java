package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.panel.BottomMenuPanel;
import presentation.panel.LeftMenuPanel;
import presentation.panel.MobilePanel;
import presentation.panel.RightMenuPanel;

public class MainScene extends Scene implements SceneChangeListener{
    @FXML
    private BorderPane rootContainer;

    @FXML
    private MobilePanel mobilePanel;

    @FXML
    private LeftMenuPanel leftMenu;

    @FXML
    private RightMenuPanel rightMenu;

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

    @FXML
    private Button hotkeyButton;

    @FXML
    private ImageView screenImage;

    private static SceneChangeListener sceneChangeListener;

    public MainScene(){
        super(new Pane(), 300, 300);
    }

    @FXML
    public void initialize(){
        leftMenu.setLockButton(lockButton);
        leftMenu.setMuteButton(muteButton);
        leftMenu.setRotateButton(rotateButton);
        leftMenu.setVolumeButton(volumeButton);
        bottomMenu.setBackButton(backButton);
        bottomMenu.setHomeButton(homeButton);
        rightMenu.setHotkeyButton(hotkeyButton);
        rightMenu.setSceneChangeListener(this);
        mobilePanel.setScreenImage(screenImage);
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    @Override
    public void onOpenDialog() {
        this.sceneChangeListener.onOpenDialog();
    }

    @Override
    public void onCloseDialog() {
        this.sceneChangeListener.onCloseDialog();
    }
}
