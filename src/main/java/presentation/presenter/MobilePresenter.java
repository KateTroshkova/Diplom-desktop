package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.VideoInteractor;
import domain.model.DeviceInfo;
import domain.model.Screenshot;
import domain.model.events.Event;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.geometry.Point2D;
import presentation.common.TouchListener;
import presentation.view.MobileView;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobilePresenter extends TouchListener {

    @Inject
    EventInteractor eventInteractor;
    @Inject
    VideoInteractor videoInteractor;

    private final List<Disposable> subscriptions = new ArrayList<Disposable>();

    private final MobileView view;

    private static final int videoDelay = 40;
    private static final int infoDelay = 1000;

    public MobilePresenter(MobileView view) {
        this.view = view;
        subscriptions.add(Flowable
                .interval(videoDelay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        e -> {
                            try {
                                Screenshot screenshot = videoInteractor.receiveScreenshot();
                                if (view != null) {
                                    view.updateImage(screenshot);
                                }
                            } catch (NullPointerException ignore) {
                            }
                        },
                        System.out::println
                ));
        subscriptions.add(Flowable
                .interval(infoDelay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        e -> {
                            DeviceInfo info = videoInteractor.receiveFile();
                            if (view != null && info != null) {
                                view.setInfo(info);
                            }
                        },
                        System.out::println
                )
        );
    }

    public void startGesture(Point2D point) {
        clearPoints();
        addPoint(point);
    }

    public void endGesture(Point2D point) {
        addPoint(point);
        Event event = getEvent();
        eventInteractor.sendEvent(event);
    }

    public void nextGesture(Point2D point) {
        addPoint(point);
    }

    public void sendFile(File file) {
        subscriptions.add(Completable.fromAction(() -> {
                    videoInteractor.sendFile(file);
                })
                        .subscribeOn(Schedulers.io())
                        .subscribe()
        );
    }
}
