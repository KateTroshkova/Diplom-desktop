package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.HotkeyInteractor;
import domain.model.Hotkey;
import domain.model.events.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;
import presentation.view.HotkeyView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HotkeyPresenter{

    @Inject
    HotkeyInteractor hotkeyInteractor;
    @Inject
    EventInteractor eventInteractor;

    private List<Hotkey> hotkeys;

    private final List<Disposable> subscriptions = new ArrayList<>();

    private HotkeyView view;

    public HotkeyPresenter() { }

    public HotkeyPresenter(HotkeyView view) {
        this();
        this.view = view;
    }

    public void loadHotkeys() {
        subscriptions.add(
                hotkeyInteractor
                        .readAllHotkeys()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                (list) -> {
                                    hotkeys = list;
                                    if (view != null) {
                                        for (Hotkey item : list) {
                                            view.addHotkey(item.toString());
                                        }
                                    }
                                },
                                System.out::println
                        )
        );
        subscriptions.add(
                hotkeyInteractor
                        .readMobileActions()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                (actions) -> {
                                    if (view != null) {
                                        view.prepareMobileActions(FXCollections.observableArrayList(actions));
                                    }
                                },
                                System.out::println
                        )
        );
        subscriptions.add(
                hotkeyInteractor
                        .readDesktopActions()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                (actions) -> {
                                    if (view != null) {
                                        view.prepareDesktopActions(FXCollections.observableArrayList(actions));
                                    }
                                },
                                System.out::println
                        )
        );
    }

    public void addHotkey(String mobileAction, String desktopAction) {
        subscriptions.add(
                hotkeyInteractor
                        .saveHotkey(new Hotkey(mobileAction, desktopAction))
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                () -> {
                                    if (view != null) {
                                        view.update();
                                        loadHotkeys();
                                    }
                                },
                                System.out::println
                        )
        );
    }

    public void removeHotkey(String hotkey) {
        String[] actions = hotkey.split(" - ");
        subscriptions.add(
                hotkeyInteractor
                        .removeHotkey(new Hotkey(actions[0], actions[1]))
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                () -> {
                                    if (view != null) {
                                        view.update();
                                        loadHotkeys();
                                    }
                                },
                                System.out::println
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
            eventInteractor.sendEvent(new SwipeEvent(500, 0, 500, 500));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_DOWN")) {
            eventInteractor.sendEvent(new SwipeEvent(500, 500, 500, 0));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_RIGHT")) {
            eventInteractor.sendEvent(new SwipeEvent(500, 500, 0, 500));
            return;
        }
        if (input.getMobileAction().equals("SWIPE_LEFT")) {
            eventInteractor.sendEvent(new SwipeEvent(0, 500, 500, 500));
        }
    }
}
