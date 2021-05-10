package domain.interactor;

import domain.api.MobileRepositoryApi;
import domain.model.events.Event;

import javax.inject.Inject;

public class EventInteractor {

    private final MobileRepositoryApi repository;

    @Inject
    public EventInteractor(MobileRepositoryApi repository){
        this.repository = repository;
    }

    public void sendEvent(Event event){
        repository.sendEvent(event);
    }
}
