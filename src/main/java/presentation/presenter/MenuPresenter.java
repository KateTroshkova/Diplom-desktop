package presentation.presenter;

import domain.interactor.ConnectionInteractor;
import domain.interactor.EventInteractor;
import presentation.common.EventListener;

public class MenuPresenter extends EventListener {

    private EventInteractor eventInteractor;
    private ConnectionInteractor connectionInteractor;
}
