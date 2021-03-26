package data.connection;

public interface ConnectionSource {

    void connect();

    void disconnect();

    boolean isConnect();
}
