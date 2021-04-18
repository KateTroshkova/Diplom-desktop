package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import presentation.panel.HotkeyPanel;

public class HotkeyScene extends Scene implements SceneChangeListener {

    @FXML
    private HotkeyPanel hotkeyContainer;
    @FXML
    private ListView hotkeyList;
    @FXML
    private Spinner mobileSpinner;
    @FXML
    private Spinner desktopSpinner;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;

    private static SceneChangeListener sceneChangeListener;
    private static DataReadyListener dataReadyListener;

    public HotkeyScene() {
        super(new Pane(), 600, 600);
    }

    @FXML
    public void initialize() {
        hotkeyContainer.setHotkeyList(hotkeyList);
        hotkeyContainer.setMobileSpinner(mobileSpinner);
        hotkeyContainer.setDesktopSpinner(desktopSpinner);
        hotkeyContainer.setAddButton(addButton);
        hotkeyContainer.setBackButton(backButton);
        hotkeyContainer.setSceneChangeListener(this);
        dataReadyListener = hotkeyContainer;
    }

    public HotkeyScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        HotkeyScene.sceneChangeListener = sceneChangeListener;
    }

    public void prepareData(){
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
}
