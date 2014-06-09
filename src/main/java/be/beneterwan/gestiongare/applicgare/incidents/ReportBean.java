package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.applicgare.ApplicGare;

/**
 * @author bendem & Curlybear
 */
public class ReportBean extends AbstractBean implements IncidentListener {

    private final AlarmBean alarmBean;
    private final ApplicGare applicGare;

    public ReportBean(ApplicGare applicGare) {
        this.applicGare = applicGare;
        alarmBean = (AlarmBean) instanciate(AlarmBean.class);
        alarmBean.addIncidentListener(this);
    }

    public void reset(){
        alarmBean.reset();
    }

    @Override
    public void onIncident(IncidentEvent event) {
        applicGare.shitHappened(event);
    }

    @Override
    public void kill() {
        alarmBean.kill();
    }

}
