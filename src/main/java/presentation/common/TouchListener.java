package presentation.common;

import domain.interactor.GestureDetector;
import domain.model.events.Event;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public abstract class TouchListener {

    private final GestureDetector gestureDetector;

    private final ArrayList<Point2D> points;

    public TouchListener() {
        points = new ArrayList<>();
        gestureDetector = new GestureDetector();
    }

    protected void addPoint(Point2D point) {
        points.add(point);
    }

    protected void clearPoints() {
        points.clear();
    }

    protected Event getEvent() {
        return gestureDetector.recogniseGesture(points);
    }
}
