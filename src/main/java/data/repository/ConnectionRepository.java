package data.repository;

import data.connection.ConnectionSettings;
import data.connection.ConnectionSource;
import data.connection.ConnectionSourceFactory;
import domain.api.ConnectionRepositoryApi;

import javax.inject.Inject;

public class ConnectionRepository implements ConnectionRepositoryApi {

    private ConnectionSourceFactory factory;
    private ConnectionSource connection;

    @Inject
    public ConnectionRepository(){

    }

    @Override
    public void connect(String type, ConnectionSettings settings) {
        factory = ConnectionSourceFactory.getInstance(type, settings);
        connection = factory.getConnection();
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void checkNext() {
        if (connection != null) {
            connection.nextDevice();
        }
    }

    @Override
    public void checkPrev() {
        if (connection != null) {
            connection.prevDevice();
        }
    }
}
