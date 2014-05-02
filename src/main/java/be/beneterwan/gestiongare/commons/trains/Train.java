package be.beneterwan.gestiongare.commons.trains;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
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

    @Override
    public String toString() {
        return type.name() + numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Train other = (Train) obj;
        if(this.type != other.type) {
            return false;
        }
        return this.numero == other.numero;
    }

    public enum Type {
        IC, L, IR, Thalis, ICE
    }

}
