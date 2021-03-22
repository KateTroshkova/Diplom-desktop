package domain.interactor;

import domain.model.events.ClickEvent;
import domain.model.events.Event;
import domain.model.events.LongClickEvent;
import domain.model.events.SwipeEvent;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class GestureDetector {

    private static final int CLICK_LENGTH = 8;
    private static final int MOVEMENT_LENGTH = 16;

    public Event recogniseGesture(ArrayList<Point2D> points) {
        if (isEmpty(points)) return null;
        if (isClick(points)) {
            return new ClickEvent(points.get(0).getX(), points.get(0).getY());
        }
        if (isLongClick(points)) {
            return new LongClickEvent(points.get(0).getX(), points.get(0).getY());
        }
        return new SwipeEvent(
                points.get(0).getX(),
                points.get(0).getY(),
                points.get(points.size() - 1).getX(),
                points.get(points.size() - 1).getY()
        );
    }

    private boolean isEmpty(ArrayList<Point2D> points) {
        return points.size() == 0;
    }

    private boolean isClick(ArrayList<Point2D> points) {
        return points.size() < CLICK_LENGTH;
    }

    private boolean isLongClick(ArrayList<Point2D> points) {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i; j < points.size(); j++) {
                double distance = points.get(i).distance(points.get(j));
                if (distance > MOVEMENT_LENGTH) {
                    return false;
                }
            }
        }
        return true;
    }
}
