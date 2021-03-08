package presentation.common;

import domain.interactor.GestureDetector;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public abstract class TouchListener {

    private GestureDetector gestureDetector;

    private ArrayList<Point2D> points;

    void addPoint(Point2D point){
        points.add(point);
        gestureDetector.recogniseGesture((Point2D[]) points.toArray());
    }

    void clearPoints(){
        points.clear();
    }
}
