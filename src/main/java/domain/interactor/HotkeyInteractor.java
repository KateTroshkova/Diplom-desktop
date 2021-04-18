package domain.interactor;

import data.repository.DatabaseRepository;
import domain.api.DatabaseRepositoryApi;
import domain.model.Hotkey;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public class HotkeyInteractor {

    private DatabaseRepositoryApi database;

    public HotkeyInteractor() {
        database = new DatabaseRepository();
    }

    public Single<List<Hotkey>> readAllHotkeys() {
        return database.readAllHotkeys();
    }

    public Completable saveHotkey(Hotkey hotkey) {
        return database.saveHotkey(hotkey);
    }

    public Completable removeHotkey(Hotkey hotkey) {
        return database.removeHotkey(hotkey);
    }

    public Single<List<String>> readMobileActions() {
        return database.readMobileActions();
    }

    public Single<List<String>> readDesktopActions() {
        return database.readDesktopActions();
    }
}
