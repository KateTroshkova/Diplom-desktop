package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.HotkeyInteractor;
import domain.model.Hotkey;
import domain.model.events.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import presentation.common.EventListener;

import java.util.ArrayList;
import java.util.List;

public class HotkeyPresenter extends EventListener {

    private HotkeyInteractor hotkeyInteractor;
    private EventInteractor eventInteractor;

    private List<Hotkey> hotkeys;

    private List<Disposable> subsriptions = new ArrayList<>();

    public HotkeyPresenter() {
        eventInteractor = new EventInteractor();
        hotkeyInteractor = new HotkeyInteractor();
    }

    public void loadHotkeys() {
        subsriptions.add(
                hotkeyInteractor
                        .readAllHotkeys()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                (list) -> {
                                    hotkeys = list;
                                },
                                (error) -> {
                                }
                        )
        );
    }

    public void handleEvent(KeyEvent event) {
        Hotkey input = null;
        for (Hotkey hotkey : hotkeys) {
            if (hotkey.getDesktopAction().equals(event.getCode().name())) {
                input = hotkey;
            }
        }
        if (input == null) return;
        if (input.getMobileAction().equals("KEYCODE_BACK")) {
            eventInteractor.sendEvent(new BackEvent());
            return;
        }
        if (input.getMobileAction().equals("KEYCODE_HOME")) {
            eventInteractor.sendEvent(new HomeEvent());
            return;
        }
        if (input.getMobileAction().equals("KEYCODE_POWER")) {
            eventInteractor.sendEvent(new LockEvent(true));
            return;
        }
        if (input.getMobileAction().equals("KEYCODE_VOLUME_UP")) {
            eventInteractor.sendEvent(new VolumeEvent(true));
            return;
        }
        if (input.getMobileAction().equals("KEYCODE_VOLUME_DOWN")) {
            eventInteractor.sendEvent(new VolumeEvent(false));
            return;
        }
        if (input.getMobileAction().equals("ROTATE_90")) {
            eventInteractor.sendEvent(new RotateEvent(90));
            return;
        }
        if (input.getMobileAction().equals("ROTATE_180")) {
            eventInteractor.sendEvent(new RotateEvent(180));
            return;
        }
        if (input.getMobileAction().equals("ROTATE_270")) {
            eventInteractor.sendEvent(new RotateEvent(270));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_UP")) {
            eventInteractor.sendEvent(new SwipeEvent(100, 100, 100, 0));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_DOWN")) {
            eventInteractor.sendEvent(new SwipeEvent(100, 0, 100, 100));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_RIGHT")) {
            eventInteractor.sendEvent(new SwipeEvent(0, 100, 100, 0));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_LEFT")) {
            eventInteractor.sendEvent(new SwipeEvent(100, 100, 0, 100));
        }
    }
}
