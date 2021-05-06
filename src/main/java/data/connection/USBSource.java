package data.connection;

import domain.common.ADBHelper;
import domain.common.FileUtils;
import domain.model.DeviceInfo;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.*;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class USBSource extends ConnectionSource {

    private boolean isConnect;
    private Disposable connection;
    private final ADBHelper adb;

    public USBSource() {
        adb = new ADBHelper();
    }

    @Override
    public void connect() {
        findDevices();
        String mobilePath = FileUtils.baseMobilePath + "/mobile_info.txt";
        String pcPath = FileUtils.baseDesktopPath;
        prepareFileDirectory();
        connection = Flowable
                .interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        (time) -> {
                            try {
                                int result = adb.executeCommand("adb " + getCurrentDevice(true) + " pull " + mobilePath + " " + pcPath);
                                if (result == 0) {
                                    isConnect = true;
                                } else {
                                    handleConnectionError();
                                }
                            } catch (IOException e) {
                                handleConnectionError();
                            }
                            System.out.println("connection " + isConnect);
                        },
                        e -> connect()
                );
    }

    @Override
    public void disconnect() {
        connection.dispose();
        isConnect = false;
        Disposable deleteProcess = Completable.fromAction(this::receiveVideo)
                .andThen(Completable.fromAction(this::deleteTempFiles))
                .delay(2, TimeUnit.SECONDS)
                .andThen(Completable.fromAction(this::deleteTempFiles))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> {
                        },
                        e -> {
                        }
                );
    }

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    private void findDevices() {
        try {
            String result = adb.executeCommandForResult("adb devices");
            devices.clear();
            String[] serialNumbers = result.split(" ");
            Collections.addAll(devices, serialNumbers);
            currentDeviceIndex = 0;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveVideo() {
        try {
            String mobilePath = FileUtils.baseMobilePath + "/record1.mp4";
            String pcPath = FileUtils.baseDesktopPath;
            //adb.executeCommand("adb " + getCurrentDevice(true) + " pull " + mobilePath + " " + pcPath);
            File file = new File(pcPath + "\\mobile_info.txt");
            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            DeviceInfo info = new DeviceInfo(resultStringBuilder.toString());
            if (info.isVideoNeeded()) {
                adb.executeCommand("adb " + getCurrentDevice(true) + " pull " + mobilePath + " " + pcPath);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
