package domain.interactor;

import data.repository.ConnectionRepository;
import domain.api.ConnectionRepositoryApi;

public class ConnectionInteractor {
    private ConnectionRepositoryApi repository;

    public ConnectionInteractor(){
        repository = new ConnectionRepository();
    }

    public void connect(String type){
        repository.connect(type);
    }

    public void disconnect(){
        repository.disconnect();
    }
}
