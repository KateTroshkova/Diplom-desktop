package domain.interactor;

import data.repository.MobileRepository;
import domain.api.MobileRepositoryApi;
import domain.model.events.Event;

public class EventInteractor {

    private MobileRepositoryApi repository;

    public EventInteractor(){
        repository = new MobileRepository();
    }

    public void sendEvent(Event event){
        repository.sendEvent(event);
    }
}
