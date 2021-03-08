package domain.interactor;

import domain.api.MobileRepositoryApi;
import domain.model.Screenshot;

import java.io.File;

public class VideoInteractor {

    private MobileRepositoryApi repository;

    Screenshot receiveScreenshot() {
        return repository.receiveScreenshot();
    }

    void sendFile(File file) {
        repository.sendFile(file);
    }

    File receiveFile() {
        return repository.receiveFile();
    }
}
