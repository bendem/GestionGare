package be.beneterwan.gestiongare.applicgare.incidents;

/**
 * @author bendem & Curlybear
 */
public enum IncidentType {
    Retard(3),
    Manifestation(7),
    Greve(17);

    private final int multiple;

    IncidentType(int multiple) {
        this.multiple = multiple;
    }

    public static IncidentType getFromNumber(int number) {
        for(IncidentType type : values()) {
            if(number % type.multiple == 0) {
                return type;
            }
        }
        return null;
    }

}
