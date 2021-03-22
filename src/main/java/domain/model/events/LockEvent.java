package domain.model.events;

public class LockEvent extends Event{
    private boolean isLocked;

    public LockEvent(boolean isLocked){
        this.isLocked = isLocked;
    }
}
