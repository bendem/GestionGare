package be.beneterwan.gestiongare.applicgare.events;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrameEventHandler;
import be.beneterwan.gestiongare.logger.CustomLogger;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.w3c.dom.events.EventException;

/**
 * @author bendem et Curlybear
 */
public abstract class AbstractEventHandler implements ActionListener { // TODO Using (String) getActionCommand is wrong...

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrameEventHandler.class.getSimpleName());
    protected static final Map<String, EventHandler> handlerList = new HashMap<>();

    public void addListener(Button button, EventHandler handler) {
        button.addActionListener(this);
        handlerList.put(button.getActionCommand(), handler);
    }

    public void addListener(JComboBox<?> comboBox, EventHandler handler) {
        comboBox.addActionListener(this);
        handlerList.put(comboBox.getActionCommand(), handler);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("Action performed : " + event.getActionCommand());
        if(handlerList.containsKey(event.getActionCommand())) {
            handlerList.get(event.getActionCommand()).execute(event);
        } else {
            throw new EventException((short) 0, "No handler registered");
        }
    }

}
