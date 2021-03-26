package data.connection;

import domain.common.ADBHelper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class USBSource implements ConnectionSource {

    private boolean isConnect;
    private Disposable connection;
    private ADBHelper adb;

    public USBSource() {
        adb = new ADBHelper();
    }

    @Override
    public void connect() {
        String mobilePath = "/storage/emulated/0/Pictures/mobile_info.txt";
        String pcPath = "C:\\Users\\Екатерина\\Desktop";
        connection = Flowable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        (time) -> {
                            try {
                                int result = adb.executeCommand("adb pull " + mobilePath + " " + pcPath);
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
    }

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    private void handleConnectionError() {
        if (isConnect) {
            disconnect();
        }
        isConnect = false;
    }
}
