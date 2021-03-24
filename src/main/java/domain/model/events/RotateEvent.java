package domain.model.events;

public class RotateEvent extends Event {
    private int degree;

    public RotateEvent(int degree) {
        this.degree = degree % 360;
    }

    public int getDegree() {
        return degree/90;
    }
}
