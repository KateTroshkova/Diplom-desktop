package domain.model;

import javafx.scene.image.Image;

public class Screenshot {

    private Image screenshot;
    private int width;
    private int height;
    private String mobileInfo;
    private long timestamp;

    public Screenshot(Image screenshot, int width, int height, String mobileInfo, long timestamp) {
        this.screenshot = screenshot;
        this.width = width;
        this.height = height;
        this.mobileInfo = mobileInfo;
        this.timestamp = timestamp;
    }

    public Image getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(Image screenshot) {
        this.screenshot = screenshot;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(String mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
