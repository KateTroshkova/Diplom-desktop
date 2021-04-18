package presentation.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.DataReadyListener;
import presentation.SceneChangeListener;
import presentation.presenter.HotkeyPresenter;
import presentation.view.HotkeyView;

public class HotkeyPanel extends VBox implements HotkeyView, DataReadyListener {

    private ListView<String> hotkeyList;
    private Spinner<String> mobileSpinner;
    private Spinner<String> desktopSpinner;
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
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(actions);
        mobileSpinner.setValueFactory(valueFactory);
    }

    @Override
    public void prepareDesktopActions(ObservableList<String> actions) {
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(actions);
        desktopSpinner.setValueFactory(valueFactory);
    }

    public void prepareData() {

    }

    public ListView<String> getHotkeyList() {
        return hotkeyList;
    }

    public void setHotkeyList(ListView<String> hotkeyList) {
        this.hotkeyList = hotkeyList;
    }

    public Spinner getMobileSpinner() {
        return mobileSpinner;
    }

    public void setMobileSpinner(Spinner mobileSpinner) {
        this.mobileSpinner = mobileSpinner;
    }

    public Spinner getDesktopSpinner() {
        return desktopSpinner;
    }

    public void setDesktopSpinner(Spinner desktopSpinner) {
        this.desktopSpinner = desktopSpinner;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
        this.addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            presenter.addHotkey(mobileSpinner.getValue(), desktopSpinner.getValue());
        });
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
        this.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
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
