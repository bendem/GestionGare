package be.beneterwan.gestiongare.commons.eventmanagement;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

/**
 * @author bendem et Curlybear
 */
public class EventManager implements ActionListener {

    private static final Logger LOGGER = new CustomLogger(EventManager.class.getSimpleName());
    protected static final Map<Object, List<EventHandler>> handlerList = new HashMap<>();

    public void addListener(Button button, EventHandler handler) {
        button.addActionListener(this);
        registerHandler(button, handler);
    }

    public void addListener(JButton button, EventHandler handler) {
        button.addActionListener(this);
        registerHandler(button, handler);
    }

    public void addListener(JComboBox<?> comboBox, EventHandler handler) {
        comboBox.addActionListener(this);
        registerHandler(comboBox, handler);
    }

    public void addListener(JMenuItem item, EventHandler handler) {
        item.addActionListener(this);
        registerHandler(item, handler);
    }

    protected void registerHandler(Object source, EventHandler handler) {
        if(!handlerList.containsKey(source)) {
            handlerList.put(source, new ArrayList<EventHandler>());
        }
        handlerList.get(source).add(handler);
    }

    protected void dispatchEvent(EventObject event) {
        if(handlerList.containsKey(event.getSource())) {
            for(EventHandler handler : handlerList.get(event.getSource())) {
                handler.execute(event);
            }
        } else {
            throw new Error("No handler registered");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.fine(event.paramString());
        dispatchEvent(event);
    }

}
