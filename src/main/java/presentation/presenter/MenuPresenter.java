package presentation.presenter;

import domain.model.ConnectionSettings;
import domain.interactor.ConnectionInteractor;
import domain.interactor.EventInteractor;
import domain.model.events.*;
import presentation.view.MenuView;

import javax.inject.Inject;

public class MenuPresenter{

    private static MenuPresenter instance;

    @Inject
    EventInteractor eventInteractor;
    @Inject
    ConnectionInteractor connectionInteractor;

    private MenuView view;

    private int currentRotation;

    private boolean isLocked;

    public static MenuPresenter getInstance(){
        if (instance == null){
            instance = new MenuPresenter();
        }
        return instance;
    }

    private MenuPresenter() { }

    public void connect(String type, ConnectionSettings settings){
        connectionInteractor.connect(type, settings);
    }

    public void disconnect(){
        connectionInteractor.disconnect();
    }

    public void enterWifi(){
        view.showWifiInputView(true);
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

    public void next(){
        connectionInteractor.checkNext();
    }

    public void prev(){
        connectionInteractor.checkPrev();
    }

    public void setMenuApi(MenuView menuView) {
        this.view = menuView;
    }
}
