package domain.interactor;

import domain.api.MobileRepositoryApi;
import domain.model.events.Event;

public class EventInteractor {

    private MobileRepositoryApi repository;

    void sendEvent(Event event){
        repository.sendEvent(event);
    }
}