package presentation.panel;

import data.connection.ConnectionSettings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;
import presentation.presenter.MenuPresenter;

public class RightMenuPanel extends VBox {

    private Button hotkeyButton;
    private Button usbConnectionButton;
    private Button ipConnectionButton;
    private Button disconnectButton;
    private TextField ipField;

    private SceneChangeListener sceneChangeListener;
    private MenuPresenter presenter;

    public RightMenuPanel(){
        presenter = new MenuPresenter();
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
            ConnectionSettings settings = new ConnectionSettings();
            settings.setPhoneIP(ipField.getText());
            presenter.connect("IP", settings);
        });
    }

    public void setDisconnectButton(Button disconnectButton) {
        this.disconnectButton = disconnectButton;
        this.disconnectButton.setOnAction(e -> {
            presenter.disconnect();
        });
    }

    public void setIpField(TextField ipField){
        this.ipField = ipField;
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }
}
