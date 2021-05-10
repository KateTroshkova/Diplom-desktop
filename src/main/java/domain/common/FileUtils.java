package domain.common;

public class FileUtils {

    public static final String baseMobilePath = "/storage/emulated/0/Pictures/diplom";
    public static final String baseDesktopPath = "\\Diplom";
    public static final String deviceInfoDesktopPath = baseDesktopPath + "\\mobile_info.txt";
    public static final String deviceInfoMobilePath=baseMobilePath + "/mobile_info.txt";
    public static final int screenCount = 20;

    public static String getScreenName(int index) {
        return baseDesktopPath + "\\screenshot" + index + ".png";
    }
}
