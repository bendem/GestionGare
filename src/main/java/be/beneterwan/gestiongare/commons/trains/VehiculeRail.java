package be.beneterwan.gestiongare.commons.trains;

import java.io.Serializable;

/**
 * @author bendem & Curlybear
 */
abstract public class VehiculeRail implements Serializable {

    protected int numero;
    protected int anneeMiseEnService;

    public VehiculeRail(int numero, int anneeMiseEnService) {
        this.numero = numero;
        this.anneeMiseEnService = anneeMiseEnService;
    }

    public int getNumero() {
        return numero;
    }

    public int getAnneeMiseEnService() {
        return anneeMiseEnService;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAnneeMiseEnService(int anneeMiseEnService) {
        this.anneeMiseEnService = anneeMiseEnService;
    }

}
