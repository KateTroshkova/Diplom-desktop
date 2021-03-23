package presentation.panel;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import presentation.SceneChangeListener;

public class RightMenuPanel extends VBox {

    private Button hotkeyButton;

    private SceneChangeListener sceneChangeListener;

    public void setHotkeyButton(Button hotkeyButton) {
        this.hotkeyButton = hotkeyButton;
        this.hotkeyButton.setOnAction(e ->
                sceneChangeListener.onOpenDialog()
        );
    }

    public void setSceneChangeListener(SceneChangeListener sceneChangeListener) {
        this.sceneChangeListener = sceneChangeListener;
    }
}
