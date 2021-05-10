package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.panel.BottomMenuPanel;
import presentation.panel.LeftMenuPanel;
import presentation.panel.MobilePanel;
import presentation.panel.RightMenuPanel;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainScene extends Scene implements SceneChangeListener{

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
    private Button usbButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button sendButton;

    @FXML
    private Button ipButton;

    @FXML
    private TextField ipField;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView prevDeviceButton;

    @FXML
    private ImageView nextDeviceButton;

    @FXML
    private Label infoLabel;

    private static SceneChangeListener sceneChangeListener;

    @SuppressWarnings("unused")
    public MainScene(){
        super(new Pane(), 0, 0);
    }

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    @FXML
    public void initialize(){
        setLocalization();
        fillLeftPanel();
        fillBottomPanel();
        fillRightPanel();
        fillCenterPanel();
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        MainScene.sceneChangeListener = sceneChangeListener;
    }

    @Override
    public void onOpenDialog() {
        sceneChangeListener.onOpenDialog();
    }

    @Override
    public void onCloseDialog() {
        sceneChangeListener.onCloseDialog();
    }

    private void setLocalization(){
        Locale current = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle("i18n.string", current);
        hotkeyButton.setText(res.getString("label.hotkey"));
        disconnectButton.setText(res.getString("label.stop"));
        sendButton.setText(res.getString("label.send"));
        usbButton.setText(res.getString("text.USB"));
        ipButton.setText(res.getString("text.IP"));
        nextButton.setText(res.getString("text.next"));
    }

    private void fillRightPanel(){
        rightMenu.setHotkeyButton(hotkeyButton);
        rightMenu.setDisconnectButton(disconnectButton);
        rightMenu.setUsbConnectionButton(usbButton);
        rightMenu.setIPConnectionButton(ipButton);
        rightMenu.setIpField(ipField);
        rightMenu.setNextButton(nextButton);
        rightMenu.setSceneChangeListener(this);
    }

    private void fillLeftPanel(){
        leftMenu.setLockButton(lockButton);
        leftMenu.setMuteButton(muteButton);
        leftMenu.setRotateButton(rotateButton);
        leftMenu.setVolumeButton(volumeButton);
    }

    private void fillBottomPanel(){
        bottomMenu.setBackButton(backButton);
        bottomMenu.setHomeButton(homeButton);
        bottomMenu.setNextButton(nextDeviceButton);
        bottomMenu.setPrevButton(prevDeviceButton);
    }

    private void fillCenterPanel(){
        mobilePanel.setSendButton(sendButton);
        mobilePanel.setInfoLabel(infoLabel);
    }
}
