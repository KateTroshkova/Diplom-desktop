package data.repository;

import data.connection.ConnectionSourceFactory;
import domain.api.MobileRepositoryApi;
import domain.model.Screenshot;
import domain.model.events.*;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public class MobileRepository implements MobileRepositoryApi {

    private ConnectionSourceFactory factory;

    public MobileRepository() {

    }

    @Override
    public void sendEvent(Event event) {
        try {
            Process process = Runtime.getRuntime().exec(prepareCommand(event));
            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Screenshot receiveScreenshot() {
        String mobilePath = "/storage/emulated/0/Pictures/screenshot1.jpg";
        String pcPath = "C:\\Users\\Екатерина\\Desktop";
        try {
            Process process = Runtime.getRuntime().exec("adb pull " + mobilePath + " " + pcPath);
            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            process.waitFor();
            File file = new File("C:\\Users\\Екатерина\\Desktop\\screenshot1.jpg");
            Image image = new Image(file.toURI().toString());
            return new Screenshot(image, (int)image.getWidth(), (int)image.getHeight(), "", 0);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendFile(File file) {

    }

    @Override
    public File receiveFile() {
        return null;
    }

    private String prepareCommand(Event event) {
        if (event instanceof BackEvent) {
            return "adb shell input keyevent KEYCODE_BACK";
        }
        if (event instanceof ClickEvent) {
            return "adb shell input tap " + ((ClickEvent) event).getX() + " " + ((ClickEvent) event).getY();
        }
        if (event instanceof HomeEvent) {
            return "adb shell input keyevent KEYCODE_HOME";
        }
        if (event instanceof LockEvent) {
            return "adb shell input keyevent KEYCODE_POWER";
        }
        if (event instanceof LongClickEvent) {
            return "adb shell input tap --longpress " + ((LongClickEvent) event).getX() + " " + ((LongClickEvent) event).getY();
        }
        if (event instanceof RotateEvent) {
            return "adb shell settings put system accelerometer_rotation 0\n adb shell settings put system user_rotation " + ((RotateEvent) event).getDegree();
        }
        if (event instanceof SwipeEvent) {
            double fromX = ((SwipeEvent) event).getFromX();
            double fromY = ((SwipeEvent) event).getFromY();
            double toX = ((SwipeEvent) event).getToX();
            double toY = ((SwipeEvent) event).getToY();
            return "adb shell input swipe " + fromX + " " + fromY + " " + toX + " " + toY;
        }
        if (event instanceof VolumeEvent) {
            if (((VolumeEvent) event).isOn()) {
                return "adb shell input keyevent KEYCODE_VOLUME_UP";
            } else {
                return "adb shell input keyevent KEYCODE_VOLUME_DOWN";
            }
        }
        return "";
    }
}
