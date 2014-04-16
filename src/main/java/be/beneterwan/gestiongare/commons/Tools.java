package be.beneterwan.gestiongare.commons;

/**
 * @author bendem et Curlybear
 */
public class Tools {

    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            // Discard silently
        }
    }

}
