package presentation.panel;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;
import presentation.presenter.MenuPresenter;

public class RightMenuPanel extends VBox {

    private Button hotkeyButton;
    private Button usbConnectionButton;
    private Button disconnectButton;

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
            presenter.connect("USB");
        });
    }

    public void setDisconnectButton(Button disconnectButton) {
        this.disconnectButton = disconnectButton;
        this.disconnectButton.setOnAction(e -> {
            presenter.disconnect();
        });
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }
}
