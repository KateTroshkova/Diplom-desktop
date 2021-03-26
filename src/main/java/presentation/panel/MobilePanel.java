package presentation.panel;

import domain.model.Screenshot;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import presentation.presenter.MobilePresenter;
import presentation.view.MobileView;

public class MobilePanel extends ImageView implements MobileView {

    private MobilePresenter presenter;

    public MobilePanel() {
        presenter = new MobilePresenter(this);
        setOnMousePressed(this::onGestureStarted);
        setOnMouseReleased(this::onGestureFinished);
        setOnMouseDragged(this::onNewPoint);
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
            setFitWidth(screenshot.getWidth() / 2.0);
            setFitHeight(screenshot.getHeight() / 2.0);
            setImage(screenshot.getScreenshot());
        }
    }
}
