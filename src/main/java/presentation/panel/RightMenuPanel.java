package presentation.panel;

import domain.interactor.ConnectionInteractor;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;

public class RightMenuPanel extends VBox {

    private Button hotkeyButton;
    private Button usbConnectionButton;
    private Button disconnectButton;
    private ConnectionInteractor connectionInteractor;

    private SceneChangeListener sceneChangeListener;

    public RightMenuPanel(){
        connectionInteractor = new ConnectionInteractor();
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
            connectionInteractor.connect("USB");
        });
    }

    public void setDisconnectButton(Button disconnectButton) {
        this.disconnectButton = disconnectButton;
        this.disconnectButton.setOnAction(e -> {
            connectionInteractor.disconnect();
        });
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }
}
