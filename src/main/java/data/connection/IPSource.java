package data.connection;

import domain.common.ADBHelper;
import domain.common.FileUtils;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IPSource extends ConnectionSource {

    private boolean isConnect;
    private Disposable connection;
    private final ADBHelper adb;
    private String phoneIP;

    public IPSource(String phoneIP) {
        adb = new ADBHelper();
        this.phoneIP = phoneIP;
    }

    @Override
    public void connect() {
        try {
            setUPWirelessConnection(phoneIP);
            findDevices();
            String mobilePath = FileUtils.baseMobilePath + "/mobile_info.txt";
            String pcPath = FileUtils.baseDesktopPath;
            prepareFileDirectory();
            connection = Flowable
                    .interval(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            (time) -> {
                                try {
                                    int result = adb.executeCommand("adb " + getCurrentDevice() + " pull " + mobilePath + " " + pcPath);
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
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            isConnect = false;
        }
    }

    @Override
    public void disconnect() {
        connection.dispose();
        isConnect = false;
        deleteTempFiles();
    }

    private void setUPWirelessConnection(String phoneIP) throws IOException, InterruptedException {
        int result = adb.executeCommand("adb tcpip 5555");
        if (result != 0) throw new IOException();
        result = adb.executeCommand("adb connect " + phoneIP);
        if (result != 0) throw new IOException();
        adb.executeCommand("adb devices");
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
}
