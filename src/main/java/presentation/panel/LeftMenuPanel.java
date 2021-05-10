package presentation.panel;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import presentation.di.DaggerInjector;
import presentation.di.Injector;
import presentation.presenter.MenuPresenter;

public class LeftMenuPanel extends VBox {

    private ImageView rotateButton;

    private ImageView volumeButton;

    private ImageView muteButton;

    private ImageView lockButton;

    private final MenuPresenter presenter;

    public LeftMenuPanel(){
        Injector injector = DaggerInjector.create();
        presenter = MenuPresenter.getInstance();
        injector.injectMenuPresenter(presenter);
    }

    public void setRotateButton(ImageView rotateButton) {
        this.rotateButton = rotateButton;
        rotateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> presenter.rotate());
    }

    public void setVolumeButton(ImageView volumeButton) {
        this.volumeButton = volumeButton;
        volumeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> presenter.volume());
    }

    public void setMuteButton(ImageView muteButton) {
        this.muteButton = muteButton;
        muteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> presenter.mute());
    }

    public void setLockButton(ImageView lockButton) {
        this.lockButton = lockButton;
        lockButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> presenter.lock());
    }
}
