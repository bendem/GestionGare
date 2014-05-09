package be.beneterwan.gestiongare.applicpostes;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostes {

    private static ApplicPostes instance;

    public ApplicPostes() {
        ApplicPostesFrame applicPostesFrame = new ApplicPostesFrame(this);
    }

    public static void main(String[] args) {
        instance = new ApplicPostes();
    }

}
