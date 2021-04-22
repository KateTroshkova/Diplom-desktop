package data.connection;

import domain.common.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ConnectionSource {

    protected boolean isConnect;
    protected List<String> devices = new ArrayList<>();
    protected int currentDeviceIndex;

    public abstract void connect();

    public abstract void disconnect();

    public boolean isConnect() {
        return isConnect;
    }

    public void nextDevice() {
        currentDeviceIndex++;
        if (currentDeviceIndex >= devices.size()) {
            currentDeviceIndex = 0;
        }
    }

    public void prevDevice() {
        currentDeviceIndex--;
        if (currentDeviceIndex < 0) {
            currentDeviceIndex = devices.size() - 1;
        }
    }

    public String getCurrentDevice() {
        return "-s " + devices.get(currentDeviceIndex);
    }

    protected void handleConnectionError() {
        if (isConnect) {
            disconnect();
        }
        isConnect = false;
    }

    protected void deleteTempFiles() {
        File info = new File(FileUtils.baseDesktopPath + "\\mobile_info.txt");
        if (info.exists()) {
            info.delete();
        }
        for (int i = 0; i < 20; i++) {
            File screenshot = new File(FileUtils.baseDesktopPath + "\\screenshot" + i + ".jpg");
            if (screenshot.exists()) {
                screenshot.delete();
            }
        }
    }

    protected void prepareFileDirectory() {
        File root = new File(FileUtils.baseDesktopPath);
        if (!root.exists()) {
            root.mkdirs();
        }
    }
}
