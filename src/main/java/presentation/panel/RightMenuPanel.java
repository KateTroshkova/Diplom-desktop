package presentation.panel;

import data.connection.ConnectionSettings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;
import presentation.presenter.HotkeyPresenter;
import presentation.presenter.MenuPresenter;

public class RightMenuPanel extends VBox implements MenuApi {

    private Button hotkeyButton;
    private Button usbConnectionButton;
    private Button ipConnectionButton;
    private Button disconnectButton;
    private TextField ipField;
    private Button nextButton;

    private SceneChangeListener sceneChangeListener;
    private MenuPresenter presenter;
    private HotkeyPresenter hotkeyPresenter;

    public RightMenuPanel() {
        presenter = MenuPresenter.getInstance();
        presenter.setMenuApi(this);
        hotkeyPresenter = new HotkeyPresenter();
        this.setOnKeyPressed((event) -> {
            hotkeyPresenter.handleEvent(event);
        });
        hotkeyPresenter.loadHotkeys();
    }

    public void setHotkeyButton(Button hotkeyButton) {
        this.hotkeyButton = hotkeyButton;
        this.hotkeyButton.setOnAction(e ->
                sceneChangeListener.onOpenDialog()
        );
    }

    public void setUsbConnectionButton(Button usbConnectionButton) {
        this.usbConnectionButton = usbConnectionButton;
        this.usbConnectionButton.setOnAction(e -> {
            presenter.connect("USB", new ConnectionSettings());
        });
    }

    public void setIPConnectionButton(Button ipConnectionButton) {
        this.ipConnectionButton = ipConnectionButton;
        this.ipConnectionButton.setOnAction(e -> {
            presenter.enterWifi();
        });
    }

    public void setDisconnectButton(Button disconnectButton) {
        this.disconnectButton = disconnectButton;
        this.disconnectButton.setOnAction(e -> {
            presenter.disconnect();
        });
    }

    public void setIpField(TextField ipField) {
        this.ipField = ipField;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
        this.nextButton.setOnAction(e -> {
            ConnectionSettings settings = new ConnectionSettings();
            settings.setPhoneIP(ipField.getText());
            presenter.connect("IP", settings);
        });
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }

    @Override
    public void showWifiInputView(boolean show) {
        this.ipField.setVisible(show);
        this.nextButton.setVisible(show);
    }
}
