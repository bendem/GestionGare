package be.beneterwan.gestiongare.applicgare;

/**
 * @author bendem et Curlybear
 */
public class DateFormat {

    private String timeFormat;
    private String dateFormat;
    private Country country;

    public DateFormat() {
        // TODO Default values!!
        this("", "", Country.France);
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
        France,
        RoyaumeUni,
        Allemagne,
        Italie,
        USA;
    }

}
