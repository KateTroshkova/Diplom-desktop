package domain.interactor;

import data.connection.ConnectionSettings;
import data.repository.ConnectionRepository;
import domain.api.ConnectionRepositoryApi;

public class ConnectionInteractor {
    private ConnectionRepositoryApi repository;

    public ConnectionInteractor(){
        repository = new ConnectionRepository();
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
