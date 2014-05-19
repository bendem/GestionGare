package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 * @author bendem & Curlybear
 */
public class OccupationHangarTableModel extends AbstractTableModel {

    private final ApplicDepotFrame frame;
    private final Map<Integer, HoraireTrain> storedTrains;

    public OccupationHangarTableModel(ApplicDepotFrame frame) {
        super();
        this.frame = frame;
        this.storedTrains = frame.getStoredTrains();
    }

    @Override
    public int getRowCount() {
        return storedTrains.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
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
                return horaire.getTrain().getWagons().size();
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
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch(column) {
            case 0:
            case 2:
                return Integer.class;
            case 1:
                return String.class;
            default:
                throw new IllegalArgumentException("Invalid Index");
        }
    }

}