package domain.api;

import domain.model.Hotkey;

import java.util.List;

public interface DatabaseRepositoryApi {

    List<Hotkey> readAllHotkeys();

    void saveHotkey(Hotkey hotkey);
}
