package domain.interactor;

import domain.api.MobileRepositoryApi;
import domain.model.DeviceInfo;
import domain.model.Screenshot;

import javax.inject.Inject;
import java.io.File;

public class VideoInteractor {

    private final MobileRepositoryApi repository;

    @Inject
    public VideoInteractor(MobileRepositoryApi mobileRepositoryApi){
        this.repository = mobileRepositoryApi;
    }

    public Screenshot receiveScreenshot() {
        return repository.receiveScreenshot();
    }

    public void sendFile(File file) {
        repository.sendFile(file);
    }

    public DeviceInfo receiveFile() {
        return repository.receiveFile();
    }
}
