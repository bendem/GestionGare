package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 * @author bendem & Curlybear
 */
public class OccupationHangarTableModel extends AbstractTableModel {

    private final ApplicDepot applicDepot;
    private final Map<Integer, HoraireTrain> storedTrains;

    OccupationHangarTableModel(ApplicDepot applicDepot) {
        super();
        this.applicDepot = applicDepot;
        this.storedTrains = applicDepot.getFrame().getStoredTrains();
    }

    @Override
    public int getRowCount() {
        return storedTrains.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ++rowIndex;

        if(columnIndex == 0) {
            return rowIndex;
        }

        if(!storedTrains.containsKey(rowIndex) || storedTrains.get(rowIndex) == null) {
            return null;
        }

        HoraireTrain horaire = storedTrains.get(rowIndex);

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
                return "Nbr de wagons";
            case 3:
                return "Présent";
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
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Boolean.class;
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(column != 4 || storedTrains.get(row+1) == null) {
            return false;
        }

        HoraireTrain horaire = storedTrains.get(row+1);
        return horaire.getState().equals(HoraireTrain.State.Inbound);
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        if(!isCellEditable(row, col)) {
            return;
        }

        applicDepot.getFrame().getStoredTrains().get(row+1);
        fireTableDataChanged();
    }

}