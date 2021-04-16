package data.database.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "desktop_action")
public class DesktopAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desktop_id", updatable = false, nullable = false)
    private int id;
    @Column(name = "desktop_name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "desktop_action", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotkeyEntity> hotkey;

    public DesktopAction() {

    }

    public DesktopAction(String name) {
        this.name = name;
    }

    public DesktopAction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addHotkey(HotkeyEntity hotkey) {
        this.hotkey.add(hotkey);
        hotkey.setDesktopAction(this);
    }

    public void removeHotkey(HotkeyEntity hotkey) {
        this.hotkey.remove(hotkey);
        hotkey.setDesktopAction(null);
    }

    public List<HotkeyEntity> getHotkey() {
        return hotkey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesktopAction that = (DesktopAction) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
