package be.beneterwan.gestiongare.trains;

/**
 * @author bendem et Curlybear
 */
public class TrainWithoutLocomotiveException extends Exception {

    /**
     * Creates a new instance of <code>TrainWithoutLocomotiveException</code> without detail message.
     */
    public TrainWithoutLocomotiveException() {
    }

    /**
     * Constructs an instance of <code>TrainWithoutLocomotiveException</code> with the specified detail message.
     * <p>
     * @param msg the detail message.
     */
    public TrainWithoutLocomotiveException(String msg) {
        super(msg);
    }

}
