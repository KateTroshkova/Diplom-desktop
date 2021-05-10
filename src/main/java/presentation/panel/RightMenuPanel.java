package presentation.panel;

import domain.model.ConnectionSettings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;
import presentation.di.DaggerInjector;
import presentation.di.Injector;
import presentation.presenter.HotkeyPresenter;
import presentation.presenter.MenuPresenter;
import presentation.view.MenuView;

public class RightMenuPanel extends VBox implements MenuView {

    private Button hotkeyButton;

    private Button usbConnectionButton;

    private Button ipConnectionButton;

    private Button disconnectButton;

    private TextField ipField;

    private Button nextButton;

    private SceneChangeListener sceneChangeListener;

    private final MenuPresenter menuPresenter;

    private final HotkeyPresenter hotkeyPresenter;

    public RightMenuPanel() {
        Injector injector = DaggerInjector.create();
        menuPresenter = MenuPresenter.getInstance();
        injector.injectMenuPresenter(menuPresenter);
        menuPresenter.setMenuApi(this);
        hotkeyPresenter = new HotkeyPresenter();
        injector.injectHotkeyPresenter(hotkeyPresenter);
        this.setOnKeyPressed(hotkeyPresenter::handleEvent);
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
        this.usbConnectionButton.setOnAction(e -> menuPresenter.connect("USB", new ConnectionSettings()));
    }

    public void setIPConnectionButton(Button ipConnectionButton) {
        this.ipConnectionButton = ipConnectionButton;
        this.ipConnectionButton.setOnAction(e -> menuPresenter.enterWifi());
    }

    public void setDisconnectButton(Button disconnectButton) {
        this.disconnectButton = disconnectButton;
        this.disconnectButton.setOnAction(e -> menuPresenter.disconnect());
    }

    public void setIpField(TextField ipField) {
        this.ipField = ipField;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
        this.nextButton.setOnAction(e -> {
            ConnectionSettings settings = new ConnectionSettings();
            settings.setPhoneIP(ipField.getText());
            menuPresenter.connect("IP", settings);
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
