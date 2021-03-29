package data.connection;

public class ConnectionSourceFactory {

    private ConnectionSource connection;
    private static String currentType="";

    public static ConnectionSourceFactory instance;

    public static ConnectionSourceFactory getInstance(String type){
        if (instance == null || !currentType.equals(type)){
            instance = new ConnectionSourceFactory(type);
            currentType = type;
        }
        return instance;
    }

    private ConnectionSourceFactory(String type){
        if (type.equals("IP")) connection = new IPSource();
        if (type.equals("Wifi")) connection = new WifiSource();
        if (type.equals("USB")) connection = new USBSource();
    }

    public ConnectionSource getConnection() {
        return connection;
    }
}
