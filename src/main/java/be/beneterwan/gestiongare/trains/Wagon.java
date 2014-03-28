package be.beneterwan.gestiongare.trains;

/**
 * @author bendem et Curlybear
 */
public class Wagon extends VehiculeRail {

    protected float longueur;
    protected Type type;

    public Wagon(float longueur, Type type, int numero, int anneeMiseEnService) {
        super(numero, anneeMiseEnService);
        this.longueur = longueur;
        this.type = type;
    }

    public enum Type {
        Voyageur, Marchandise
    }

}
