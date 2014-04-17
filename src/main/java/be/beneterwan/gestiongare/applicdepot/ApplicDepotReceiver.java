/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;

/**
 *
 * @author Bear
 */
public class ApplicDepotReceiver extends AbstractRunnable{
    private final boolean cancelled;
    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());
    private final NetworkStringReceiver receiver = new NetworkStringReceiver(50005);
    private final ApplicDepot applicDepot;
    
    public ApplicDepotReceiver(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
        cancelled = false;
    }

    @Override
    protected void work() {
        String message = receiver.getMessage();
        if(!"RIEN".equals(message)) {
            applicDepot.addApplicDepotMessage(message);
        }
    }

    @Override
    protected void shutdown() {
        receiver.endReceiving();
    }
}
