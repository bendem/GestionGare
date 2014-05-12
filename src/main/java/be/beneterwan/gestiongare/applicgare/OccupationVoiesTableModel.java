package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author bendem & Curlybear
 */
public class OccupationVoiesTableModel extends AbstractTableModel {
    private final List<HoraireTrain> inboundTrains;

    OccupationVoiesTableModel(List<HoraireTrain> inboundTrains) {
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
        switch(columnIndex){
            case 0:
                return inboundTrains.get(rowIndex).getQuai();
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
    
}
