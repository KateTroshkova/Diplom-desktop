package presentation.presenter;

import domain.interactor.EventInteractor;
import domain.interactor.VideoInteractor;
import domain.model.DeviceInfo;
import domain.model.Screenshot;
import domain.model.events.Event;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import javafx.geometry.Point2D;
import presentation.common.TouchListener;
import presentation.view.MobileView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobilePresenter extends TouchListener {

    private EventInteractor eventInteractor;
    private VideoInteractor videoInteractor;
    private List<Disposable> subscriptions = new ArrayList<Disposable>();
    private MobileView view;

    public MobilePresenter(MobileView view) {
        this.view = view;
        eventInteractor = new EventInteractor();
        videoInteractor = new VideoInteractor();
        subscriptions.add(Flowable
                .interval(40, TimeUnit.MILLISECONDS)
                .subscribe(
                        e -> {
                            try {
                                Screenshot screenshot = videoInteractor.receiveScreenshot();
                                view.updateImage(screenshot);
                            } catch (NullPointerException ignore) {
                            }
                        },
                        System.out::println
                ));
        subscriptions.add(Flowable
                .interval(1, TimeUnit.SECONDS)
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
        subscriptions.add(
                Completable.fromAction(() -> {
                    videoInteractor.sendFile(file);
                })
                        .subscribe()
        );
    }
}
