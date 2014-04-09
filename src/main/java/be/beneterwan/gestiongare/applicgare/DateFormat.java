package be.beneterwan.gestiongare.applicgare;

import java.util.TimeZone;

/**
 * @author bendem et Curlybear
 */
public class DateFormat {

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
            return timeZone;
        }

    }

}
