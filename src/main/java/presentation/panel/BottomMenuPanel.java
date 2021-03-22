package presentation.panel;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import presentation.presenter.MenuPresenter;

public class BottomMenuPanel extends HBox {

    //@FXML
    private ImageView backButton;

    //@FXML
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
