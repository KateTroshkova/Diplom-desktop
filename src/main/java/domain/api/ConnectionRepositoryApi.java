package domain.api;

import data.connection.ConnectionSettings;

public interface ConnectionRepositoryApi {

    void connect(String type, ConnectionSettings settings);

    void disconnect();
}
