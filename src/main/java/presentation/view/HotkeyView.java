package presentation.view;

import javafx.collections.ObservableList;

import java.util.List;

public interface HotkeyView {

    void addHotkey(String hotkey);

    void prepareMobileActions(ObservableList<String> actions);

    void prepareDesktopActions(ObservableList<String> actions);

    void update(List<String> hotkeys);
}
