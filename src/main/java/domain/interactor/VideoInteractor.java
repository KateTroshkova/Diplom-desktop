package domain.interactor;

import data.repository.MobileRepository;
import domain.api.MobileRepositoryApi;
import domain.model.Screenshot;

import java.io.File;

public class VideoInteractor {

    private MobileRepositoryApi repository;

    public VideoInteractor(){
        repository = new MobileRepository();
    }

    public Screenshot receiveScreenshot() {
        return repository.receiveScreenshot();
    }

    public void sendFile(File file) {
        repository.sendFile(file);
    }

    public File receiveFile() {
        return repository.receiveFile();
    }
}
