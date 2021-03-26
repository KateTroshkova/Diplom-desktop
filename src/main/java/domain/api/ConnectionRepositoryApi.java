package domain.api;

public interface ConnectionRepositoryApi {

    void connect(String type);

    void disconnect();
}
