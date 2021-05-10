package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import presentation.panel.HotkeyPanel;

import java.util.Locale;
import java.util.ResourceBundle;

public class HotkeyScene extends Scene implements SceneChangeListener {

    @FXML
    private HotkeyPanel hotkeyContainer;
    @FXML
    private ListView<String> hotkeyList;
    @FXML
    private ComboBox<String> mobileSpinner;
    @FXML
    private ComboBox<String> desktopSpinner;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;

    private static SceneChangeListener sceneChangeListener;
    private static DataReadyListener dataReadyListener;

    @SuppressWarnings("unused")
    public HotkeyScene() {
        super(new Pane(), 600, 600);
    }

    public HotkeyScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    @FXML
    public void initialize() {
        setLocalization();
        hotkeyContainer.setHotkeyList(hotkeyList);
        hotkeyContainer.setMobileComboBox(mobileSpinner);
        hotkeyContainer.setDesktopComboBox(desktopSpinner);
        hotkeyContainer.setAddButton(addButton);
        hotkeyContainer.setBackButton(backButton);
        hotkeyContainer.setSceneChangeListener(this);
        dataReadyListener = hotkeyContainer;
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        HotkeyScene.sceneChangeListener = sceneChangeListener;
    }

    public void prepareData() {
        dataReadyListener.onPrepareData();
    }

    @Override
    public void onOpenDialog() {
        sceneChangeListener.onOpenDialog();
    }

    @Override
    public void onCloseDialog() {
        sceneChangeListener.onCloseDialog();
    }

    private void setLocalization() {
        Locale current = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle("i18n.string", current);
        addButton.setText(res.getString("text.add"));
        backButton.setText(res.getString("text.back"));
    }
}
