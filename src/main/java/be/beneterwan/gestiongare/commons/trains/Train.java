package be.beneterwan.gestiongare.commons.trains;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class Train implements Serializable {

    public static final Logger LOGGER = new CustomLogger(Train.class.getSimpleName());

    protected final Set<VehiculeRail> wagons = new HashSet<>();
    protected Locomotive locomotive;

    protected Type type;
    protected int numero;

    public Train(Locomotive locomotive, Type type, int numero) throws TrainWithoutLocomotiveException {
        this(locomotive, null, type, numero);
    }

    public Train(Locomotive locomotive, Set<VehiculeRail> wagons, Type type, int numero) throws TrainWithoutLocomotiveException {
        if(locomotive == null) {
            throw new TrainWithoutLocomotiveException();
        }
        this.locomotive = locomotive;
        if(wagons != null) {
            this.wagons.addAll(wagons);
        }
        this.type = type;
        this.numero = numero;
    }

    public Train add(VehiculeRail vehiculeRail) {
        if(vehiculeRail != null) {
            wagons.add(vehiculeRail);
        }
        return this;
    }

    public static Train deserialize(String serialized) {
        // TODO deserialize stuff
        return null;
    }

    public Set<VehiculeRail> getWagons() {
        return wagons;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public Type getType() {
        return type;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return type.name() + numero;
    }

    public enum Type {
        IC, L, IR, Thalis, ICE
    }

}
