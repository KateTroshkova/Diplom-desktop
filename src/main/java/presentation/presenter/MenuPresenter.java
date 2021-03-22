package presentation.presenter;

import domain.interactor.ConnectionInteractor;
import domain.interactor.EventInteractor;
import domain.model.events.*;
import presentation.common.EventListener;

public class MenuPresenter extends EventListener {

    private EventInteractor eventInteractor;
    private ConnectionInteractor connectionInteractor;

    private int currentRotation;
    private boolean isLocked;

    public MenuPresenter() {
        eventInteractor = new EventInteractor();
    }

    public void rotate() {
        currentRotation += 90;
        eventInteractor.sendEvent(new RotateEvent(currentRotation));
    }

    public void lock() {
        isLocked = !isLocked;
        eventInteractor.sendEvent(new LockEvent(isLocked));
    }

    public void volume() {
        eventInteractor.sendEvent(new VolumeEvent(true));
    }

    public void mute() {
        eventInteractor.sendEvent(new VolumeEvent(false));
    }

    public void back() {
        eventInteractor.sendEvent(new BackEvent());
    }

    public void home() {
        eventInteractor.sendEvent(new HomeEvent());
    }
}
