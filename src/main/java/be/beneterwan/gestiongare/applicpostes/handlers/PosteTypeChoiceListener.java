package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;
import javax.swing.JComboBox;

/**
 * @author bendem et Curlybear
 */
public class PosteTypeChoiceListener implements EventHandler {

    private final ApplicPostes applicPostes;

    public PosteTypeChoiceListener(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        JComboBox<ApplicPostes.Type> choice = applicPostes.getFrame().getComboBoxPostes();
        applicPostes.setType((ApplicPostes.Type) choice.getSelectedItem());
        choice.setEnabled(false);
        applicPostes.getFrame().getButtonValider().setEnabled(false);
    }

}
