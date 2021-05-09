package data.database.converter;

import data.database.entity.DesktopAction;
import data.database.entity.HotkeyEntity;
import data.database.entity.MobileAction;
import domain.common.Mapper;
import domain.model.Hotkey;

import javax.inject.Inject;

public class HotkeyMapper implements Mapper<Hotkey, HotkeyEntity> {

    @Inject
    public HotkeyMapper(){

    }

    @Override
    public HotkeyEntity fromBusiness(Hotkey hotkey) {
        return new HotkeyEntity(
                new MobileAction(hotkey.getMobileAction()),
                new DesktopAction(hotkey.getDesktopAction())
        );
    }

    @Override
    public Hotkey fromDto(HotkeyEntity hotkeyEntity) {
        return new Hotkey(
                hotkeyEntity.getMobileAction().getName(),
                hotkeyEntity.getDesktopAction().getName()
        );
    }
}
