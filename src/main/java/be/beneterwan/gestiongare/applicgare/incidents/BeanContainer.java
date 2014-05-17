package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.beans.Beans;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class BeanContainer {

    private static final Logger LOGGER = new CustomLogger(BeanContainer.class);
    private final ThreadRandomGenerator threadRandomGenerator;
    private final StateBean stateBean;
    private final AlarmBean alarmBean;
    private final ReportBean reportBean;

    public BeanContainer() {
        stateBean = (StateBean) instanciate(StateBean.class.getName());
        threadRandomGenerator = new ThreadRandomGenerator(stateBean, 15);
        alarmBean = (AlarmBean) instanciate(AlarmBean.class.getName());
        stateBean.addPropertyChangeListener(alarmBean);
        reportBean = (ReportBean) instanciate(ReportBean.class.getName());
        alarmBean.addIncidentListener(reportBean);

        threadRandomGenerator.start();
    }

    public void kill() {
        threadRandomGenerator.cancel();
    }

    private Object instanciate(String className) {
        try {
            return Beans.instantiate(null, className);
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not instanciate " + className, ex);
        }
        return null;
    }

}
