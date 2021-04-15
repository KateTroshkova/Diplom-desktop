package data.connection;

public class ConnectionSourceFactory {

    private ConnectionSource connection;
    private static String currentType = "";

    public static ConnectionSourceFactory instance;

    public static ConnectionSourceFactory getInstance(String type, ConnectionSettings settings) {
        if (instance == null || !currentType.equals(type)) {
            instance = new ConnectionSourceFactory(type, settings);
            currentType = type;
        }
        return instance;
    }

    public static ConnectionSourceFactory getLastInstance() {
        if (instance == null) {
            instance = new ConnectionSourceFactory("USB", new ConnectionSettings());
            currentType = "USB";
        }
        return instance;
    }

    private ConnectionSourceFactory(String type, ConnectionSettings settings) {
        if (type.equals("IP")) connection = new IPSource(settings.getPhoneIP());
        if (type.equals("Wifi")) connection = new WifiSource();
        if (type.equals("USB")) connection = new USBSource();
    }

    public ConnectionSource getConnection() {
        return connection;
    }
}
