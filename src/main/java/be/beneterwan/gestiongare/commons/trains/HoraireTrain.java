package be.beneterwan.gestiongare.commons.trains;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bendem et Curlybear
 */
public class HoraireTrain implements Serializable {

    private Train train;
    private String destination;
    private String origine;
    private Date arrivee;
    private Date depart;
    private int quai;

    public HoraireTrain(Train train, String destination, String origine, Date arrivee, Date depart, int quai) {
        this.train = train;
        this.destination = destination;
        this.origine = origine;
        this.arrivee = arrivee;
        this.depart = depart;
        this.quai = quai;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Date getArrivee() {
        return arrivee;
    }

    public void setArrivee(Date arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public int getQuai() {
        return quai;
    }

    public void setQuai(int quai) {
        this.quai = quai;
    }

}
