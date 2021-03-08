package data.connection;

public class ConnectionSourceFactory {

    private ConnectionSource connection;

    ConnectionSourceFactory(String type){
        if (type=="IP") connection = new IPSource();
        if (type=="Wifi") connection = new WifiSource();
        if (type=="USB") connection = new USBSource();
    }
}
