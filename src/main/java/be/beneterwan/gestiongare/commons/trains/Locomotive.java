package be.beneterwan.gestiongare.commons.trains;

/**
 * @author bendem & Curlybear
 */
public class Locomotive extends VehiculeRail {

    protected final float puissance;

    public Locomotive(float puissance, int numero, int anneeMiseEnService) {
        super(numero, anneeMiseEnService);
        this.puissance = puissance;
    }

}
