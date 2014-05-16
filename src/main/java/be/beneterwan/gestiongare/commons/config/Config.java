package be.beneterwan.gestiongare.commons.config;

/**
 *
 * @author bendem & Curlybear
 */
public enum Config {

    PortApplicInToApplicGare("port.ApplicInToApplicGare", 50_010),
    PortApplicOutToApplicGare("port.ApplicOutToApplicGare", 50_011),
    PortApplicDepotToApplicGare("port.ApplicDepotToApplicGare", 50_015),
    PortApplicGareToApplicIn("port.ApplicGareToApplicIn", 50_000),
    PortApplicGareToApplicOut("port.ApplicGareToApplicOut", 50_001),
    PortApplicGareToApplicDepot("port.ApplicGareToApplicDepot", 50_005),
    IpApplicIn("ip.ApplicIn", "127.0.0.1"),
    IpApplicOut("ip.ApplicOut", "127.0.0.1"),
    IpApplicGare("ip.ApplicGare", "127.0.0.1"),
    IpApplicDepot("ip.ApplicDepot", "127.0.0.1"),
    ;

    private final String node;
    private final Object defaultValue;

    Config(String node, Object defaultValue) {
        this.node = node;
        this.defaultValue = defaultValue;
    }

    public String getNode() {
        return node;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}
