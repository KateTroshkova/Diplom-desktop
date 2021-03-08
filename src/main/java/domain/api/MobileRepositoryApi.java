package domain.api;

import domain.model.Screenshot;
import domain.model.events.Event;

import java.io.File;

public interface MobileRepositoryApi {

    void sendEvent(Event event);

    Screenshot receiveScreenshot();

    void sendFile(File file);

    File receiveFile();
}
