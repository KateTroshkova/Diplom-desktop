package data.connection;

public class ConnectionSourceFactory {

    private ConnectionSource connection;
    private static String currentType;

    public static ConnectionSourceFactory instance;

    public static ConnectionSourceFactory getInstance(String type){
        if (instance == null || currentType!=type){
            instance = new ConnectionSourceFactory(type);
        }
        return instance;
    }

    private ConnectionSourceFactory(String type){
        if (type=="IP") connection = new IPSource();
        if (type=="Wifi") connection = new WifiSource();
        if (type=="USB") connection = new USBSource();
    }

    public ConnectionSource getConnection() {
        return connection;
    }
}
