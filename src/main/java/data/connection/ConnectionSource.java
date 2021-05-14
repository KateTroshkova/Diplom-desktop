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
        return false;
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

    public String getCurrentDevice(boolean isUSB) {
        if (isUSB) {
            return "-s " + devices.get(currentDeviceIndex);
        } else {
            return "";
        }
    }

    protected void handleConnectionError() {
        if (isConnect) {
            disconnect();
        }
        isConnect = false;
    }

    protected void deleteTempFiles() {
        deletePhoneInfo();
        deleteScreenshots();
    }

    protected void prepareFileDirectory() {
        File root = new File(FileUtils.baseDesktopPath);
        if (!root.exists()) {
            root.mkdirs();
        }
    }

    protected void deleteScreenshots(){
        for (int i = 0; i < FileUtils.screenCount; i++) {
            File screenshot = new File(FileUtils.getScreenName(i));
            if (screenshot.exists()) {
                screenshot.delete();
            }
        }
    }

    private void deletePhoneInfo(){
        File info = new File(FileUtils.deviceInfoDesktopPath);
        if (info.exists()) {
            info.delete();
        }
    }
}
