package presentation.panel;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import presentation.presenter.MenuPresenter;

public class BottomMenuPanel extends HBox {

    private ImageView backButton;

    private ImageView homeButton;

    private MenuPresenter presenter;

    public BottomMenuPanel(){
        presenter = new MenuPresenter();
    }

    public void setBackButton(ImageView backButton) {
        this.backButton = backButton;
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            presenter.back();
        });
    }

    public void setHomeButton(ImageView homeButton) {
        this.homeButton = homeButton;
        homeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            presenter.home();
        });
    }
}
