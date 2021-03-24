package domain.model.events;

public class ClickEvent extends Event{
    private double x;
    private double y;

    public ClickEvent(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
