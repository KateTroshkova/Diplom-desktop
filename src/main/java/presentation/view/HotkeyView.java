package presentation.view;

import javafx.collections.ObservableList;

public interface HotkeyView {

    void addHotkey(String hotkey);

    void prepareMobileActions(ObservableList<String> actions);

    void prepareDesktopActions(ObservableList<String> actions);
}
