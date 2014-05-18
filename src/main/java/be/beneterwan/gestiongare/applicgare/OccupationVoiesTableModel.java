package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 * @author bendem & Curlybear
 */
public class OccupationVoiesTableModel extends AbstractTableModel {

    private final Map<Integer, HoraireTrain> inboundTrains;

    OccupationVoiesTableModel(Map<Integer, HoraireTrain> inboundTrains) {
        super();
        this.inboundTrains = inboundTrains;
    }

    @Override
    public int getRowCount() {
        return inboundTrains.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // The Map goes from 1 to TrainManager.NB_VOIES
        ++rowIndex;

        if(columnIndex == 0) {
            return rowIndex;
        }

        if(!inboundTrains.containsKey(rowIndex) || inboundTrains.get(rowIndex) == null) {
            return null;
        }

        HoraireTrain horaire = inboundTrains.get(rowIndex);

        switch(columnIndex) {
            case 1:
                return horaire.getTrain().getType().name() + horaire.getTrain().getNumero();
            case 2:
                return String.format("%02d:%02d", horaire.getArriveeHeure(), horaire.getArriveeMinute());
            case 3:
                return String.format("%02d:%02d", horaire.getDepartHeure(), horaire.getDepartMinute());
            case 4:
                return horaire.getState().equals(HoraireTrain.State.Stationned);
            case 5:
                return 1337; //TODO
            default:
                throw new IllegalArgumentException("Invalid Index");

        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Voie";
            case 1:
                return "Numéro";
            case 2:
                return "Arrivée";
            case 3:
                return "Départ";
            case 4:
                return "Présent";
            case 5:
                return "Retard";
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch(column) {
            case 0:
            case 5:
                return Integer.class;
            case 1:
            case 2:
            case 3:
                return String.class;
            case 4:
                return Boolean.class;
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

}
