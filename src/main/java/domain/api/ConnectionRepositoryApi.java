package domain.api;

import domain.model.ConnectionSettings;

public interface ConnectionRepositoryApi {

    void connect(String type, ConnectionSettings settings);

    void disconnect();

    void checkNext();

    void checkPrev();
}
