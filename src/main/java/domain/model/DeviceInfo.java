package domain.model;

import org.json.JSONObject;

public class DeviceInfo {

    private String id;
    private String brand;
    private String model;
    private String device;
    private boolean isVideoNeeded;
    private String fileToSend;

    public DeviceInfo(String id, String brand, String model, String device, boolean isVideoNeeded, String fileToSend) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.device = device;
        this.isVideoNeeded = isVideoNeeded;
        this.fileToSend = fileToSend;
    }

    public DeviceInfo(String json){
        JSONObject obj = new JSONObject(json);
        id = (String) obj.get("id");
        brand = (String) obj.get("brand");
        model = (String) obj.get("model");
        device = (String) obj.get("device");
        isVideoNeeded = (boolean) obj.get("isVideoNeeded");
        fileToSend = (String) obj.get("fileToSend");
    }

    public String getzId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public boolean isVideoNeeded() {
        return isVideoNeeded;
    }

    public void setVideoNeeded(boolean videoNeeded) {
        isVideoNeeded = videoNeeded;
    }

    public String getFileToSend() {
        return fileToSend;
    }

    public void setFileToSend(String fileToSend) {
        this.fileToSend = fileToSend;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", device='" + device + '\'' +
                '}';
    }
}
