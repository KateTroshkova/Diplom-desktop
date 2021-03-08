package data.repository;

import data.database.DatabaseHelper;
import domain.api.DatabaseRepositoryApi;
import domain.model.Hotkey;

import java.util.List;

public class DatabaseRepository implements DatabaseRepositoryApi {

    private DatabaseHelper helper;

    @Override
    public List<Hotkey> readAllHotkeys() {
        return null;
    }

    @Override
    public void saveHotkey(Hotkey hotkey) {

    }
}
