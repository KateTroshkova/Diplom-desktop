package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.panel.BottomMenuPanel;
import presentation.panel.LeftMenuPanel;
import presentation.panel.MobilePanel;
import presentation.panel.RightMenuPanel;

import java.util.Locale;
import java.util.ResourceBundle;

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
    public Button usbButton;

    @FXML
    public Button disconnectButton;

    @FXML
    public Button sendButton;

    @FXML
    public Button ipButton;

    @FXML
    public TextField ipField;

    @FXML
    public Button nextButton;

    @FXML
    public ImageView prevDeviceButton;

    @FXML
    public ImageView nextDeviceButton;

    private static SceneChangeListener sceneChangeListener;

    public MainScene(){
        super(new Pane(), 300, 300);
    }

    @FXML
    public void initialize(){
        Locale current = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle("i18n.string", current);
        hotkeyButton.setText(res.getString("label.hotkey"));
        disconnectButton.setText(res.getString("label.stop"));
        sendButton.setText(res.getString("label.send"));
        usbButton.setText(res.getString("text.USB"));
        ipButton.setText(res.getString("text.IP"));
        nextButton.setText(res.getString("text.next"));
        leftMenu.setLockButton(lockButton);
        leftMenu.setMuteButton(muteButton);
        leftMenu.setRotateButton(rotateButton);
        leftMenu.setVolumeButton(volumeButton);
        bottomMenu.setBackButton(backButton);
        bottomMenu.setHomeButton(homeButton);
        bottomMenu.setNextButton(nextDeviceButton);
        bottomMenu.setPrevButton(prevDeviceButton);
        rightMenu.setHotkeyButton(hotkeyButton);
        rightMenu.setDisconnectButton(disconnectButton);
        rightMenu.setUsbConnectionButton(usbButton);
        rightMenu.setIPConnectionButton(ipButton);
        rightMenu.setIpField(ipField);
        rightMenu.setNextButton(nextButton);
        rightMenu.setSceneChangeListener(this);
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        MainScene.sceneChangeListener = sceneChangeListener;
    }

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    @Override
    public void onOpenDialog() {
        sceneChangeListener.onOpenDialog();
    }

    @Override
    public void onCloseDialog() {
        sceneChangeListener.onCloseDialog();
    }
}
