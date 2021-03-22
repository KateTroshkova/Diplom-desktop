package domain.model.events;

public class VolumeEvent extends Event{
    private boolean isOn;

    public VolumeEvent(boolean isOn){
        this.isOn = isOn;
    }
}
