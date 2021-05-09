package domain.interactor;

import data.repository.MobileRepository;
import domain.api.MobileRepositoryApi;
import domain.model.DeviceInfo;
import domain.model.Screenshot;

import javax.inject.Inject;
import java.io.File;

public class VideoInteractor {

    private MobileRepositoryApi repository;

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
