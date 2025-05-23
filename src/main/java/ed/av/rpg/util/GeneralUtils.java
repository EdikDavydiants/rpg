package ed.av.rpg.util;

public class GeneralUtils {

    public static final String HIGHLIGHTED_HEX_IMG_URL = "highlighted_hex.png";
    public static final String HEX_IMG_URL = "hex.png";

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException("Exception in method GeneralUtils.sleep(long ms)");
        }
    }
}
