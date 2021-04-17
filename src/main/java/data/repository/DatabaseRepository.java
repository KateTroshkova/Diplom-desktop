package data.repository;

import data.database.converter.HotkeyMapper;
import data.database.dao.HotkeyDao;
import data.database.entity.HotkeyEntity;
import domain.api.DatabaseRepositoryApi;
import domain.model.Hotkey;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements DatabaseRepositoryApi {

    private HotkeyDao dao;

    public DatabaseRepository() {
        dao = new HotkeyDao();
    }

    @Override
    public Single<List<Hotkey>> readAllHotkeys() {
        return Single.fromCallable(() -> {
            List<HotkeyEntity> dbEntities = dao.readAll();
            HotkeyMapper mapper = new HotkeyMapper();
            List<Hotkey> hotkeys = new ArrayList<>();
            for (HotkeyEntity entity : dbEntities) {
                hotkeys.add(mapper.fromDto(entity));
            }
            return hotkeys;
        });
    }

    @Override
    public Completable saveHotkey(Hotkey hotkey) {
        return Completable.fromAction(()->{
            HotkeyMapper mapper = new HotkeyMapper();
            HotkeyEntity businessHotkey = mapper.fromBusiness(hotkey);
        });
    }

    @Override
    public Completable removeHotkey(Hotkey hotkey) {
        return Completable.fromAction(()->{
            HotkeyMapper mapper = new HotkeyMapper();
            HotkeyEntity businessHotkey = mapper.fromBusiness(hotkey);
        });
    }
    //desktopAction_desktop_id int4, mobileAction_mobile_id int4,
}
