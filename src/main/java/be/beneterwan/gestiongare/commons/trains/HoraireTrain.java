package be.beneterwan.gestiongare.commons.trains;

import java.io.Serializable;

/**
 * @author bendem et Curlybear
 */
public class HoraireTrain implements Serializable {

    private Train train;
    private String destination;
    private String origine;
    private int arrivee;
    private int depart;
    private int quai;

    public HoraireTrain(Train train, String destination, String origine, int heureArrivee, int minuteArrivee, int heureDepart, int minuteDepart, int quai) {
        this(train, destination, origine, heureArrivee*60 + minuteArrivee, heureDepart*60 + minuteDepart, quai);
    }

    public HoraireTrain(Train train, String destination, String origine, int arrivee, int depart, int quai) {
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

    public int getArrivee() {
        return arrivee;
    }

    public void setArrivee(int arrivee) {
        this.arrivee = arrivee;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public int getQuai() {
        return quai;
    }

    public void setQuai(int quai) {
        this.quai = quai;
    }

}
