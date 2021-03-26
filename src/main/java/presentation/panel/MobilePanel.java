package presentation.panel;

import domain.model.Screenshot;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import presentation.presenter.MobilePresenter;
import presentation.view.MobileView;

public class MobilePanel extends Pane implements MobileView {

    private MobilePresenter presenter;

    private ImageView screenImage;

    public MobilePanel() {
        presenter = new MobilePresenter(this);
    }

    public void setScreenImage(ImageView screenImage) {
        this.screenImage = screenImage;
        this.screenImage.setOnMousePressed(this::onGestureStarted);
        this.screenImage.setOnMouseReleased(this::onGestureFinished);
        this.screenImage.setOnMouseDragged(this::onNewPoint);
    }

    private void onGestureStarted(MouseEvent event) {
        presenter.startGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    private void onGestureFinished(MouseEvent event) {
        presenter.endGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    private void onNewPoint(MouseEvent event) {
        presenter.nextGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    @Override
    public void updateImage(Screenshot screenshot) {
        if (screenshot.getScreenshot()!=null) {
            screenImage.setFitWidth(screenshot.getWidth() / 2.0);
            screenImage.setFitHeight(screenshot.getHeight() / 2.0);
            setWidth(screenshot.getWidth() / 2.0);
            setHeight(screenshot.getHeight() / 2.0);
            screenImage.setImage(screenshot.getScreenshot());
        }
    }
}
