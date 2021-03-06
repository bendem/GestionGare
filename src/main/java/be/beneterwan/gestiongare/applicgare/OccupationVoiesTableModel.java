package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 * @author bendem & Curlybear
 */
public class OccupationVoiesTableModel extends AbstractTableModel {

    private final ApplicGare applicGare;
    private final Map<Integer, HoraireTrain> inboundTrains;

    OccupationVoiesTableModel(ApplicGare applicGare) {
        super();
        this.applicGare = applicGare;
        this.inboundTrains = applicGare.getTrainManager().getInboundTrains();
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
                return String.format("%02d:%02d - %s", horaire.getArriveeHeure(), horaire.getArriveeMinute(), horaire.getOrigine());
            case 3:
                return String.format("%02d:%02d - %s", horaire.getDepartHeure(), horaire.getDepartMinute(), horaire.getDestination());
            case 4:
                return horaire.getState().equals(HoraireTrain.State.Stationned);
            case 5:
                return String.valueOf(horaire.getRetard()/60) + "h" + horaire.getRetard()%60;
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
                return Integer.class;
            case 1:
            case 2:
            case 3:
            case 5:
                return String.class;
            case 4:
                return Boolean.class;
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(column != 4 || inboundTrains.get(row+1) == null) {
            return false;
        }

        HoraireTrain horaire = inboundTrains.get(row+1);
        return horaire.getState().equals(HoraireTrain.State.Inbound);
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        if(!isCellEditable(row, col)) {
            return;
        }

        applicGare.getTrainManager().trainArrived(inboundTrains.get(row+1));
        fireTableDataChanged();
    }

}
