/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.beneterwan.gestiongare.applicgare.help;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventObject;

/**
 *
 * @author Bear
 */
public class jComboBoxValueChangeHandler implements EventHandler {

    private final DateFormatDialog dialog;
    private final Calendar c = Calendar.getInstance();
    private final SimpleDateFormat s = new SimpleDateFormat();

    public jComboBoxValueChangeHandler(DateFormatDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        ApplicGareFrame parent = (ApplicGareFrame) dialog.getParent();
        DateFormat.Country tCountry;
        String tDateFormat;
        String tTimeFormat;

        tCountry = (DateFormat.Country) dialog.getComboBoxPays().getSelectedItem();
        tDateFormat = (String) dialog.getComboBoxFormatDate().getSelectedItem();
        tTimeFormat = (String) dialog.getComboBoxFormatHeure().getSelectedItem();

        if(tCountry != null){
            c.setTimeZone(tCountry.getTimeZone());
        }
        if(tDateFormat != null && tTimeFormat != null){
            s.applyPattern(dialog.comboBoxFormatDate+", "+dialog.comboBoxFormatHeure);
        }
        
        dialog.sampleContent.setText(s.format(c.getTime()));
    }
}
