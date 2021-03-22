package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.VideoInteractor;
import domain.model.events.Event;
import javafx.geometry.Point2D;
import presentation.common.EventListener;
import presentation.common.TouchListener;

public class MobilePresenter extends TouchListener {

    private EventInteractor eventInteractor;
    private VideoInteractor videoInteractor;

    public MobilePresenter(){

    }

    public void startGesture(Point2D point){
        clearPoints();
        addPoint(point);
    }

    public void endGesture(Point2D point){
        addPoint(point);
        Event event = getEvent();
        System.out.println(event);
    }

    public void nextGesture(Point2D point){
        addPoint(point);
    }
}
