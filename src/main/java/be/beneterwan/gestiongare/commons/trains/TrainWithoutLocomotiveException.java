package be.beneterwan.gestiongare.commons.trains;

/**
 * @author bendem & Curlybear
 */
public class TrainWithoutLocomotiveException extends Exception {

    public TrainWithoutLocomotiveException() {
    }

    public TrainWithoutLocomotiveException(String msg) {
        super(msg);
    }

}
