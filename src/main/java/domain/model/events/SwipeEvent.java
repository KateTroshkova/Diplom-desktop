package domain.model.events;

public class SwipeEvent extends Event{
    private double fromX;
    private double fromY;
    private double toX;
    private double toY;

    public SwipeEvent(double fromX, double fromY, double toX, double toY){
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public String toString() {
        return "SwipeEvent{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                '}';
    }
}
