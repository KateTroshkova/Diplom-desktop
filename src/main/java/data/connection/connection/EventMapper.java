package data.connection.connection;

import data.connection.entity.EventRequest;
import domain.common.Mapper;
import domain.model.events.Event;

public class EventMapper implements Mapper<Event, EventRequest> {
    @Override
    public EventRequest fromBusiness(Event event) {
        return null;
    }

    @Override
    public Event fromDto(EventRequest eventRequest) {
        return null;
    }
}
