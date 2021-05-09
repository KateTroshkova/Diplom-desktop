package domain.interactor;

import data.repository.MobileRepository;
import domain.api.MobileRepositoryApi;
import domain.model.events.Event;

import javax.inject.Inject;

public class EventInteractor {

    private MobileRepositoryApi repository;

    @Inject
    public EventInteractor(MobileRepositoryApi repository){
        this.repository = repository;
    }

    public void sendEvent(Event event){
        repository.sendEvent(event);
    }
}
