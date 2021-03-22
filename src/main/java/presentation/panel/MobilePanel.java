package presentation.panel;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import presentation.presenter.MobilePresenter;

public class MobilePanel extends Pane {

    private MobilePresenter presenter;

    public MobilePanel(){
        presenter = new MobilePresenter();
        setOnMousePressed(this::onGestureStarted);
        setOnMouseReleased(this::onGestureFinished);
        setOnMouseDragged(this::onNewPoint);
    }

    private void onGestureStarted(MouseEvent event){
        presenter.startGesture(new Point2D(event.getX(), event.getY()));
    }

    private void onGestureFinished(MouseEvent event){
        presenter.endGesture(new Point2D(event.getX(), event.getY()));
    }

    private void onNewPoint(MouseEvent event){
        presenter.nextGesture(new Point2D(event.getX(), event.getY()));
    }
}
