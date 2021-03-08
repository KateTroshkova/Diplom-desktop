package data.database.converter;

import data.database.entity.HotkeyEntity;
import domain.common.Mapper;
import domain.model.Hotkey;

public class HotkeyMapper implements Mapper<Hotkey, HotkeyEntity> {
    @Override
    public HotkeyEntity fromBusiness(Hotkey hotkey) {
        return null;
    }

    @Override
    public Hotkey fromDto(HotkeyEntity hotkeyEntity) {
        return null;
    }
}
