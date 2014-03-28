package be.beneterwan.gestiongare.trains;

/**
 * @author bendem
 */
public class Locomotive extends VehiculeRail {

    protected final float puissance;

    public Locomotive(float puissance, int numero, int anneeMiseEnService) {
        super(numero, anneeMiseEnService);
        this.puissance = puissance;
    }

}