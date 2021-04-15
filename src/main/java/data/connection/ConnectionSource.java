package data.connection;

import domain.common.FileUtils;

import java.io.File;

public abstract class ConnectionSource {

    protected boolean isConnect;

    public abstract void connect();

    public abstract void disconnect();

    public boolean isConnect(){
        return isConnect;
    };

    protected void handleConnectionError() {
        if (isConnect) {
            disconnect();
        }
        isConnect = false;
    }

    protected void deleteTempFiles(){
        File info = new File(FileUtils.baseDesktopPath + "\\mobile_info.txt");
        if (info.exists()) {
            info.delete();
        }
        for(int i=0; i<20; i++){
            File screenshot = new File(FileUtils.baseDesktopPath + "\\screenshot"+i+".jpg");
            if (screenshot.exists()) {
                screenshot.delete();
            }
        }
    }

    protected void prepareFileDirectory(){
        File root = new File(FileUtils.baseDesktopPath);
        if (!root.exists()){
            root.mkdirs();
        }
    }
}
