package domain.interactor;

import data.connection.ConnectionSettings;
import data.repository.ConnectionRepository;
import domain.api.ConnectionRepositoryApi;

import javax.inject.Inject;

public class ConnectionInteractor {
    private ConnectionRepositoryApi repository;

    @Inject
    public ConnectionInteractor(ConnectionRepositoryApi repository){
        this.repository = repository;
    }

    public void connect(String type, ConnectionSettings settings){
        repository.connect(type, settings);
    }

    public void disconnect(){
        repository.disconnect();
    }

    public void checkNext(){
        repository.checkNext();
    }

    public void checkPrev(){
        repository.checkPrev();
    }
}
