package data.connection.connection;

import data.connection.entity.ScreenshotResponse;
import domain.common.Mapper;
import domain.model.Screenshot;

public class ScreenshotMapper implements Mapper<Screenshot, ScreenshotResponse> {
    @Override
    public ScreenshotResponse fromBusiness(Screenshot screenshot) {
        return null;
    }

    @Override
    public Screenshot fromDto(ScreenshotResponse screenshotResponse) {
        return null;
    }
}
