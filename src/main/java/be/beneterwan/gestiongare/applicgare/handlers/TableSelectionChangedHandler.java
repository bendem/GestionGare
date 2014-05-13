package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;
import javax.swing.event.ListSelectionEvent;

/**
 * @author bendem & Curlybear
 */
public class TableSelectionChangedHandler implements EventHandler {

    private final ApplicGare applicGare;
    private final ApplicGareFrame frame;

    public TableSelectionChangedHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
        frame = applicGare.getFrame();
    }

    @Override
    public void execute(EventObject event) {
        if (((ListSelectionEvent) event).getValueIsAdjusting()) {
            return;
        }
        
        int selectedRow = frame.getTableOccupationVoies().getSelectedRow() + 1;
        boolean enabled = applicGare.getTrainManager().getInboundTrains().get(selectedRow) != null;
        frame.getButtonControleOut().setEnabled(enabled);
    }

}
