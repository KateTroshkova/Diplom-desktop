package data.repository;

import data.database.converter.HotkeyMapper;
import data.database.dao.DesktopActionDao;
import data.database.dao.HotkeyDao;
import data.database.dao.MobileActionDao;
import data.database.entity.DesktopAction;
import data.database.entity.HotkeyEntity;
import data.database.entity.MobileAction;
import domain.api.DatabaseRepositoryApi;
import domain.model.Hotkey;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements DatabaseRepositoryApi {

    private HotkeyDao dao;
    private MobileActionDao mobileDao;
    private DesktopActionDao desktopDao;
    private HotkeyMapper mapper;

    private List<HotkeyEntity> hotkeys;

    public DatabaseRepository(
            HotkeyDao dao,
            MobileActionDao mobileDao,
            DesktopActionDao desktopDao,
            HotkeyMapper mapper
    ) {
        this.dao = dao;
        this.mobileDao = mobileDao;
        this.desktopDao = desktopDao;
        this.mapper = mapper;
    }

    @Override
    public Single<List<String>> readMobileActions() {
        return Single.fromCallable(() -> {
            List<MobileAction> actions = mobileDao.readAll();
            List<String> names = new ArrayList<>();
            for (MobileAction action : actions) {
                names.add(action.getName());
            }
            return names;
        });
    }

    @Override
    public Single<List<String>> readDesktopActions() {
        return Single.fromCallable(() -> {
            List<DesktopAction> actions = desktopDao.readAll();
            List<String> names = new ArrayList<>();
            for (DesktopAction action : actions) {
                names.add(action.getName());
            }
            return names;
        });
    }

    @Override
    public Single<List<Hotkey>> readAllHotkeys() {
        return Single.fromCallable(() -> {
            List<HotkeyEntity> dbEntities = dao.readAll();
            this.hotkeys = dbEntities;
            List<Hotkey> hotkeys = new ArrayList<>();
            for (HotkeyEntity entity : dbEntities) {
                hotkeys.add(mapper.fromDto(entity));
            }
            return hotkeys;
        });
    }

    @Override
    public Completable saveHotkey(Hotkey hotkey) {
        return Completable.fromAction(() -> {
            MobileAction mobileAction = mobileDao.readByName(hotkey.getMobileAction());
            DesktopAction desktopAction = desktopDao.readByName(hotkey.getDesktopAction());
            HotkeyEntity dbEntity = new HotkeyEntity(mobileAction, desktopAction);
            mobileAction.setHotkey(new ArrayList<>());
            mobileAction.addHotkey(dbEntity);
            dao.save(dbEntity);
        });
    }

    @Override
    public Completable removeHotkey(Hotkey hotkey) {
        return Completable.fromAction(() -> {
            HotkeyEntity dbEntity = null;
            for (HotkeyEntity entity : hotkeys) {
                if (entity.getDesktopAction().getName().equals(hotkey.getDesktopAction()) && entity.getMobileAction().getName().equals(hotkey.getMobileAction())) {
                    dbEntity = entity;
                }
            }
            dao.delete(dbEntity);
        });
    }
}
