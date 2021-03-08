package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.HotkeyInteractor;
import presentation.common.EventListener;

public class HotkeyPresenter extends EventListener {

    private HotkeyInteractor hotkeyInteractor;
    private EventInteractor eventInteractor;
}
