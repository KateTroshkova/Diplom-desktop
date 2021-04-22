package presentation.presenter;

import data.connection.ConnectionSettings;
import domain.interactor.ConnectionInteractor;
import domain.interactor.EventInteractor;
import domain.model.events.*;
import presentation.common.EventListener;
import presentation.panel.MenuApi;

public class MenuPresenter extends EventListener {

    private EventInteractor eventInteractor;
    private ConnectionInteractor connectionInteractor;
    private MenuApi menuApi;

    private int currentRotation;
    private boolean isLocked;

    public MenuPresenter() {
        connectionInteractor = new ConnectionInteractor();
        eventInteractor = new EventInteractor();
    }

    public MenuPresenter(MenuApi menuApi) {
        this();
        setMenuApi(menuApi);
    }

    public void connect(String type, ConnectionSettings settings){
        connectionInteractor.connect(type, settings);
    }

    public void disconnect(){
        connectionInteractor.disconnect();
    }

    public void enterWifi(){
        menuApi.showWifiInputView(true);
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

    public MenuApi getMenuApi() {
        return menuApi;
    }

    public void setMenuApi(MenuApi menuApi) {
        this.menuApi = menuApi;
    }
}
