package be.beneterwan.gestiongare.applicgare.help;

import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class JComboBoxValueChangeHandler implements EventHandler {

    private final DateFormatDialog dialog;
    public JComboBoxValueChangeHandler(DateFormatDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        if(event.getSource() == dialog.getComboBoxPays()){
            dialog.getDateFormat().setCountry((DateFormat.Country) dialog.getComboBoxPays().getSelectedItem());
        } else if(event.getSource() == dialog.getComboBoxFormatDate()){
            dialog.getDateFormat().setDateFormat((String) dialog.getComboBoxFormatDate().getSelectedItem());
        } else if(event.getSource() == dialog.getComboBoxFormatHeure()){
            dialog.getDateFormat().setTimeFormat((String) dialog.getComboBoxFormatHeure().getSelectedItem());
        }
        dialog.refreshSampleContent();
    }

}
