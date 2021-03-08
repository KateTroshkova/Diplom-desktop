package domain.interactor;

import domain.api.ConnectionRepositoryApi;

public class ConnectionInteractor {
    private ConnectionRepositoryApi repository;

    void connect(){
        repository.connect();
    }

    void disconnect(){
        repository.disconnect();
    }
}
