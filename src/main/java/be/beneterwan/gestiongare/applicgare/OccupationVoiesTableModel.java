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

        if(!inboundTrains.containsKey(rowIndex) || inboundTrains.get(rowIndex) == null) {
            return null;
        }

        switch(columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return inboundTrains.get(rowIndex).getTrain().getNumero();
            case 2:
                return inboundTrains.get(rowIndex).getArriveeHeure(); //TODO BETTER
            case 3:
                return inboundTrains.get(rowIndex).getDepartHeure(); //TODO BETTER
            case 4:
                return inboundTrains.get(rowIndex).getState().equals(HoraireTrain.State.Stationned);
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

}
