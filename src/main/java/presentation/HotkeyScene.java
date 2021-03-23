package presentation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class HotkeyScene extends Scene {

    public HotkeyScene(){
        super(new Pane(), 600, 600);
    }

    @FXML
    public void initialize() {
    }

    public HotkeyScene(Parent root, double width, double height) {
        super(root, width, height);
    }
}
