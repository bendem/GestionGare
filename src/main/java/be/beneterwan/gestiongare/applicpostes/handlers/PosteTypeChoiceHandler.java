package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;
import javax.swing.JComboBox;

/**
 * @author bendem et Curlybear
 */
public class PosteTypeChoiceHandler implements EventHandler {

    private final ApplicPostes applicPostes;

    public PosteTypeChoiceHandler(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        JComboBox<ApplicPostes.Type> choice = applicPostes.getFrame().getComboBoxPostes();
        applicPostes.startApplication((ApplicPostes.Type) choice.getSelectedItem());
    }

}
