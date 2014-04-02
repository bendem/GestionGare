package be.beneterwan.gestiongare.applicgare.events;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrameEventHandler;
import be.beneterwan.gestiongare.logger.CustomLogger;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.w3c.dom.events.EventException;

/**
 * @author bendem et Curlybear
 */
public abstract class AbstractEventHandler implements ActionListener {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrameEventHandler.class.getSimpleName());
    protected static final Map<Object, List<EventHandler>> handlerList = new HashMap<>();

    public void addListener(Button button, EventHandler handler) {
        button.addActionListener(this);
        registerHandler(button, handler);
    }

    public void addListener(JComboBox<?> comboBox, EventHandler handler) {
        comboBox.addActionListener(this);
        registerHandler(comboBox, handler);
    }

    public void registerHandler(Object source, EventHandler handler) {
        if(!handlerList.containsKey(source)) {
            handlerList.put(source, new ArrayList<EventHandler>());
        }
        handlerList.get(source).add(handler);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("Action performed : " + event.getActionCommand() + ", params : " + event.paramString());
        dispatchEvent(event);
    }

    public void dispatchEvent(EventObject event) {
        if(handlerList.containsKey(event.getSource())) {
            for(EventHandler handler : handlerList.get(event.getSource())) {
                handler.execute(event);
            }
        } else {
            throw new EventException((short) 0, "No handler registered");
        }
    }

}
