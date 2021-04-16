package data.database.entity;

import javax.persistence.*;

// TODO create hotkey entity
// TODO set desktop entity
// TODO add to mobile entity
@Entity
@Table(name = "hotkeys")
public class HotkeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bond_id", updatable = false, nullable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private MobileAction mobileAction;
    @ManyToOne(fetch = FetchType.LAZY)
    private DesktopAction desktopAction;

    public HotkeyEntity() {

    }

    public HotkeyEntity(MobileAction mobileAction, DesktopAction desktopAction) {
        this.mobileAction = mobileAction;
        this.desktopAction = desktopAction;
    }

    public HotkeyEntity(int id, MobileAction mobileAction, DesktopAction desktopAction) {
        this.id = id;
        this.mobileAction = mobileAction;
        this.desktopAction = desktopAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MobileAction getMobileAction() {
        return mobileAction;
    }

    public void setMobileAction(MobileAction mobileAction) {
        this.mobileAction = mobileAction;
    }

    public DesktopAction getDesktopAction() {
        return desktopAction;
    }

    public void setDesktopAction(DesktopAction desktopAction) {
        this.desktopAction = desktopAction;
    }
}
