package domain.model;

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
}
