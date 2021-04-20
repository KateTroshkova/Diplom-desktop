package data.connection;

import domain.common.ADBHelper;
import domain.common.FileUtils;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.File;
import java.io.IOException;
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
    }

    @Override
    public void disconnect() {
        connection.dispose();
        isConnect = false;
        deleteTempFiles();
    }

    @Override
    public boolean isConnect(){
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
}
