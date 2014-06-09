package be.beneterwan.gestiongare.applicgare.dialogs.handlers;

import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.applicgare.dialogs.DateFormatDialog;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class DateFormatDialogComboBoxChangeHandler implements EventHandler {

    private final DateFormatDialog dialog;
    public DateFormatDialogComboBoxChangeHandler(DateFormatDialog dialog) {
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
