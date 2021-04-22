package presentation.panel;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import presentation.presenter.MenuPresenter;

public class BottomMenuPanel extends HBox {

    private ImageView backButton;

    private ImageView homeButton;

    private ImageView nextButton;

    private ImageView prevButton;

    private MenuPresenter presenter;

    public BottomMenuPanel(){
        presenter = MenuPresenter.getInstance();
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

    public void setNextButton(ImageView nextButton) {
        this.nextButton = nextButton;
        this.nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            presenter.next();
        });
    }

    public void setPrevButton(ImageView prevButton) {
        this.prevButton = prevButton;
        this.prevButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            presenter.prev();
        });
    }
}
