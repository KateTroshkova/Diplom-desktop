package data.repository;

import data.connection.ConnectionSource;
import data.connection.ConnectionSourceFactory;
import data.connection.USBSource;
import domain.api.MobileRepositoryApi;
import domain.common.ADBHelper;
import domain.common.FileUtils;
import domain.model.DeviceInfo;
import domain.model.Screenshot;
import domain.model.events.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class MobileRepository implements MobileRepositoryApi {

    private ConnectionSourceFactory factory;
    private ADBHelper adb;
    private ConnectionSource connection;
    private int currentIndex = 0;

    public MobileRepository() {
        adb = new ADBHelper();
    }

    @Override
    public void sendEvent(Event event) {
        lateinitConnection();
        if (connection.isConnect()) {
            try {
                adb.executeCommand(prepareCommand(event));
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Screenshot receiveScreenshot() {
        lateinitConnection();
        if (connection.isConnect()) {
            currentIndex++;
            if (currentIndex == 20) {
                currentIndex = 0;
            }
            String mobilePath = FileUtils.baseMobilePath + "/screenshot" + currentIndex + ".jpg";
            String pcPath = FileUtils.baseDesktopPath;
            File root = new File(pcPath);
            if (!root.exists()) {
                root.mkdirs();
            }
            try {
                adb.executeCommand("adb " + connection.getCurrentDevice(connection instanceof USBSource) + " pull " + mobilePath + " " + pcPath);
                File file = new File(pcPath + "\\screenshot" + currentIndex + ".jpg");
                Image image = new Image(file.toURI().toString());
                return new Screenshot(image, (int) image.getWidth(), (int) image.getHeight(), "", 0);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void sendFile(File file) {

    }

    @Override
    public DeviceInfo receiveFile() {
        if (connection.isConnect()) {
            String mobilePath = FileUtils.baseMobilePath + "/mobile_info.txt";
            String pcPath = FileUtils.baseDesktopPath;
            File root = new File(pcPath);
            if (!root.exists()) {
                root.mkdirs();
            }
            try {
                adb.executeCommand("adb " + connection.getCurrentDevice(connection instanceof USBSource) + " pull " + mobilePath + " " + pcPath);
                File file = new File(pcPath + "\\mobile_info.txt");
                StringBuilder resultStringBuilder = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        resultStringBuilder.append(line).append("\n");
                    }
                }
                DeviceInfo info = new DeviceInfo(resultStringBuilder.toString());
                if (info.getFileToSend() != null) {
                    int fileNameStart = info.getFileToSend().lastIndexOf("/");
                    int fileNameEnd = info.getFileToSend().length() - 1;
                    String fileName = info.getFileToSend().substring(fileNameStart, fileNameEnd);
                    if (!(new File(pcPath + "\\" + fileName).exists())) {
                        adb.executeCommand("adb " + connection.getCurrentDevice(connection instanceof USBSource) +
                                " pull " + info.getFileToSend() + " " + pcPath + "\\" + fileName);
                    }
                }
                return info;
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String prepareCommand(Event event) {
        if (event instanceof BackEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input keyevent KEYCODE_BACK";
        }
        if (event instanceof ClickEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input tap " + ((ClickEvent) event).getX() + " " + ((ClickEvent) event).getY();
        }
        if (event instanceof HomeEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input keyevent KEYCODE_HOME";
        }
        if (event instanceof LockEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input keyevent KEYCODE_POWER";
        }
        if (event instanceof LongClickEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input tap --longpress " + ((LongClickEvent) event).getX() + " " + ((LongClickEvent) event).getY();
        }
        if (event instanceof RotateEvent) {
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell settings put system accelerometer_rotation 0\n adb shell settings put system user_rotation " + ((RotateEvent) event).getDegree();
        }
        if (event instanceof SwipeEvent) {
            double fromX = ((SwipeEvent) event).getFromX();
            double fromY = ((SwipeEvent) event).getFromY();
            double toX = ((SwipeEvent) event).getToX();
            double toY = ((SwipeEvent) event).getToY();
            return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input swipe " + fromX + " " + fromY + " " + toX + " " + toY;
        }
        if (event instanceof VolumeEvent) {
            if (((VolumeEvent) event).isOn()) {
                return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input keyevent KEYCODE_VOLUME_UP";
            } else {
                return "adb " + connection.getCurrentDevice(connection instanceof USBSource) + " shell input keyevent KEYCODE_VOLUME_DOWN";
            }
        }
        return "";
    }

    private void lateinitConnection() {
        factory = ConnectionSourceFactory.getLastInstance();
        connection = factory.getConnection();
    }
}
