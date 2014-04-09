package be.beneterwan.gestiongare.applicgare.help;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventObject;

/**
 * @author Bear
 */
public class JComboBoxValueChangeHandler implements EventHandler {

    private final DateFormatDialog dialog;
    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat();

    public JComboBoxValueChangeHandler(DateFormatDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        ApplicGareFrame parent = (ApplicGareFrame) dialog.getParent();

        DateFormat.Country country = (DateFormat.Country) dialog.getComboBoxPays().getSelectedItem();
        String dateFormat = (String) dialog.getComboBoxFormatDate().getSelectedItem();
        String timeFormat = (String) dialog.getComboBoxFormatHeure().getSelectedItem();

        if(country != null){
            calendar.setTimeZone(country.getTimeZone());
        }
        if(dateFormat != null && timeFormat != null){
            dateFormatter.applyPattern(dateFormat + ", " + timeFormat);
        }

        dialog.sampleContent.setText(dateFormatter.format(calendar.getTime()));
    }
}
