package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class DateFormat implements Cloneable {

    private static final Logger LOGGER = new CustomLogger(DateFormat.class);

    private String timeFormat;
    private String dateFormat;
    private Country country;

    public DateFormat() {
        this("HH:mm", "dd-MM-YYYY", Country.France);
    }

    public DateFormat(String timeFormat, String dateFormat, Country country) {
        this.timeFormat = timeFormat;
        this.dateFormat = dateFormat;
        this.country = country;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public DateFormat clone() {
        try {
            return (DateFormat) super.clone();
        } catch(CloneNotSupportedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public enum Country {

        France("Europe/Paris"),
        RoyaumeUni("Europe/London"),
        Allemagne("Europe/Berlin"),
        Italie("Europe/Rome"),
        USA("America/New_York");

        private final TimeZone timeZone;

        private Country(String timeZone) {
            this.timeZone = TimeZone.getTimeZone(timeZone);
        }

        public TimeZone getTimeZone() {
            return this.timeZone;
        }

    }

}
