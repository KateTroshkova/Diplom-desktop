package presentation.panel;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.DataReadyListener;
import presentation.SceneChangeListener;
import presentation.di.DaggerInjector;
import presentation.di.Injector;
import presentation.presenter.HotkeyPresenter;
import presentation.view.HotkeyView;

public class HotkeyPanel extends VBox implements HotkeyView, DataReadyListener {

    private ListView<String> hotkeyList;

    private ComboBox<String> mobileComboBox;

    private ComboBox<String> desktopComboBox;

    private Button addButton;

    private Button backButton;

    private HotkeyPresenter presenter;

    private SceneChangeListener sceneChangeListener;

    public HotkeyPanel() {
        Injector injector = DaggerInjector.create();
        presenter = new HotkeyPresenter(this);
        injector.injectHotkeyPresenter(presenter);
    }

    @Override
    public void addHotkey(String hotkey) {
        Platform.runLater(() -> hotkeyList.getItems().add(hotkey));
    }

    @Override
    public void prepareMobileActions(ObservableList<String> actions) {
        Platform.runLater(() -> mobileComboBox.setItems(actions));
    }

    @Override
    public void prepareDesktopActions(ObservableList<String> actions) {
        Platform.runLater(() -> desktopComboBox.setItems(actions));
    }

    @Override
    public void update() {
        Platform.runLater(() -> hotkeyList.getItems().clear());
    }

    public void setHotkeyList(ListView<String> hotkeyList) {
        this.hotkeyList = hotkeyList;
        this.hotkeyList.setOnMouseClicked((event) -> presenter.removeHotkey(hotkeyList.getSelectionModel().getSelectedItem()));
    }

    public void setMobileComboBox(ComboBox<String> mobileComboBox) {
        this.mobileComboBox = mobileComboBox;
    }

    public void setDesktopComboBox(ComboBox<String> desktopComboBox) {
        this.desktopComboBox = desktopComboBox;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
        this.addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> presenter.addHotkey(mobileComboBox.getValue(), desktopComboBox.getValue()));
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
        this.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            hotkeyList.getItems().clear();
            sceneChangeListener.onCloseDialog();
        });
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }

    @Override
    public void onPrepareData() {
        presenter.loadHotkeys();
    }
}
