package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.VideoInteractor;
import presentation.common.EventListener;
import presentation.common.TouchListener;

public class MobilePresenter extends TouchListener {

    private EventInteractor eventInteractor;
    private VideoInteractor videoInteractor;
}
