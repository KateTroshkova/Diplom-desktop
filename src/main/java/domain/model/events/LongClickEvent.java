package domain.model.events;

public class LongClickEvent extends Event{
    private double x;
    private double y;

    public LongClickEvent(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "LongClickEvent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
