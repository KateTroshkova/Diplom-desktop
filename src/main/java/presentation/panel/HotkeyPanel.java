package presentation.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.DataReadyListener;
import presentation.SceneChangeListener;
import presentation.presenter.HotkeyPresenter;
import presentation.view.HotkeyView;

import java.util.List;

public class HotkeyPanel extends VBox implements HotkeyView, DataReadyListener {

    private ListView<String> hotkeyList;
    private ComboBox<String> mobileComboBox;
    private ComboBox<String> desktopComboBox;
    private Button addButton;
    private Button backButton;

    private HotkeyPresenter presenter;
    private SceneChangeListener sceneChangeListener;

    public HotkeyPanel() {
        presenter = new HotkeyPresenter(this);
    }

    @Override
    public void addHotkey(String hotkey) {
        hotkeyList.getItems().add(hotkey);
    }

    @Override
    public void prepareMobileActions(ObservableList<String> actions) {
        mobileComboBox.setItems(actions);
    }

    @Override
    public void prepareDesktopActions(ObservableList<String> actions) {
        desktopComboBox.setItems(actions);
    }

    @Override
    public void update(List<String> hotkeys) {
        hotkeyList.getItems().clear();
    }

    public void prepareData() {

    }

    public ListView<String> getHotkeyList() {
        return hotkeyList;
    }

    public void setHotkeyList(ListView<String> hotkeyList) {
        this.hotkeyList = hotkeyList;
        this.hotkeyList.setOnMouseClicked((event) -> {
            presenter.removeHotkey(hotkeyList.getSelectionModel().getSelectedItem());
        });
    }

    public ComboBox getMobileComboBox() {
        return mobileComboBox;
    }

    public void setMobileComboBox(ComboBox mobileComboBox) {
        this.mobileComboBox = mobileComboBox;
    }

    public ComboBox getDesktopComboBox() {
        return desktopComboBox;
    }

    public void setDesktopComboBox(ComboBox desktopComboBox) {
        this.desktopComboBox = desktopComboBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
        this.addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            presenter.addHotkey(mobileComboBox.getValue(), desktopComboBox.getValue());
        });
    }

    public Button getBackButton() {
        return backButton;
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
