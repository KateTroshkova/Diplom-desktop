package data.repository;

import data.connection.ConnectionSourceFactory;
import domain.api.MobileRepositoryApi;
import domain.model.Screenshot;
import domain.model.events.Event;

import java.io.File;

public class MobileRepository implements MobileRepositoryApi {

    private ConnectionSourceFactory factory;

    @Override
    public void sendEvent(Event event) {

    }

    @Override
    public Screenshot receiveScreenshot() {
        return null;
    }

    @Override
    public void sendFile(File file) {

    }

    @Override
    public File receiveFile() {
        return null;
    }
}
