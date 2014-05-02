/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import network.NetworkStringReceiver;

/**
 *
 * @author Bear
 */
public class ApplicDepotReceiver extends AbstractRunnable {
    private NetworkStringReceiver receiver;
    private final ApplicDepot applicDepot;
    
    public ApplicDepotReceiver(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
    }
    
    @Override
    protected void startup() {
        receiver = new NetworkStringReceiver(50005);
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
