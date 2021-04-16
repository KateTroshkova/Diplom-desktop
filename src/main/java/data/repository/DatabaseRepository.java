package data.repository;

import data.database.dao.HotkeyDao;
import domain.api.DatabaseRepositoryApi;
import domain.model.Hotkey;

import java.util.List;

public class DatabaseRepository implements DatabaseRepositoryApi {

    private HotkeyDao dao;

    public DatabaseRepository(){
        dao = new HotkeyDao();
    }

    @Override
    public List<Hotkey> readAllHotkeys() {
        return null;
    }

    @Override
    public void saveHotkey(Hotkey hotkey) {

    }
}
