package domain.model;

import java.util.Objects;

public class Hotkey {

    private String name;
    private String mobileAction;
    private String desktopAction;

    public Hotkey(String mobileAction, String desktopAction) {
        this.mobileAction = mobileAction;
        this.desktopAction = desktopAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileAction() {
        return mobileAction;
    }

    public void setMobileAction(String mobileAction) {
        this.mobileAction = mobileAction;
    }

    public String getDesktopAction() {
        return desktopAction;
    }

    public void setDesktopAction(String desktopAction) {
        this.desktopAction = desktopAction;
    }

    @Override
    public String toString() {
        return mobileAction + " - "+desktopAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotkey hotkey = (Hotkey) o;
        return Objects.equals(mobileAction, hotkey.mobileAction) && Objects.equals(desktopAction, hotkey.desktopAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileAction, desktopAction);
    }
}
