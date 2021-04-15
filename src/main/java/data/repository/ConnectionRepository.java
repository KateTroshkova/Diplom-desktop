package data.repository;

import data.connection.ConnectionSettings;
import data.connection.ConnectionSource;
import data.connection.ConnectionSourceFactory;
import domain.api.ConnectionRepositoryApi;

public class ConnectionRepository implements ConnectionRepositoryApi {

    private ConnectionSourceFactory factory;
    private ConnectionSource connection;

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
}
