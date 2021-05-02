package presentation.view;

import domain.model.DeviceInfo;
import domain.model.Screenshot;

public interface MobileView {

    void updateImage(Screenshot screenshot);

    void setInfo(DeviceInfo info);
}
