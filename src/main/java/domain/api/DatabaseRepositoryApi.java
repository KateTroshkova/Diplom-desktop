package domain.api;

import domain.model.Hotkey;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface DatabaseRepositoryApi {

    Single<List<Hotkey>> readAllHotkeys();

    Completable saveHotkey(Hotkey hotkey);

    Completable removeHotkey(Hotkey hotkey);
}
