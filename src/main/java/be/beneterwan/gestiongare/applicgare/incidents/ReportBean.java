package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ReportBean extends AbstractBean implements IncidentListener {

    private static final Logger LOGGER = new CustomLogger(ReportBean.class);
    private final AlarmBean alarmBean;
    private final ApplicGare applicGare;

    public ReportBean(ApplicGare applicGare) {
        this.applicGare = applicGare;
        alarmBean = (AlarmBean) instanciate(AlarmBean.class);
        alarmBean.addIncidentListener(this);
    }

    @Override
    public void onIncident(IncidentEvent event) {
        // TODO Send somehow the event to applicgare
    }

    @Override
    public void kill() {
        alarmBean.kill();
    }

}
