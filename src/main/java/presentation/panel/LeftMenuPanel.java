package presentation.panel;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.presenter.HotkeyPresenter;
import presentation.presenter.MenuPresenter;

public class LeftMenuPanel extends VBox {

    private ImageView rotateButton;

    private ImageView volumeButton;

    private ImageView muteButton;

    private ImageView lockButton;

    private MenuPresenter menuPresenter;
    private HotkeyPresenter hotkeyPresenter;

    public LeftMenuPanel(){
        menuPresenter = MenuPresenter.getInstance();
        hotkeyPresenter = new HotkeyPresenter();
        this.setOnKeyPressed((event)->{
            hotkeyPresenter.handleEvent(event);
        });
        hotkeyPresenter.loadHotkeys();
    }

    public void setRotateButton(ImageView rotateButton) {
        this.rotateButton = rotateButton;
        rotateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuPresenter.rotate();
        });
    }

    public void setVolumeButton(ImageView volumeButton) {
        this.volumeButton = volumeButton;
        volumeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuPresenter.volume();
        });
    }

    public void setMuteButton(ImageView muteButton) {
        this.muteButton = muteButton;
        muteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuPresenter.mute();
        });
    }

    public void setLockButton(ImageView lockButton) {
        this.lockButton = lockButton;
        lockButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuPresenter.lock();
        });
    }
}
