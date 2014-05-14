package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ThreadRandomGenerator extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(ThreadRandomGenerator.class.getSimpleName());

    private final ApplicGare applicGare;
    private final NumberConsumer consumer;
    private final int declencher;

    public ThreadRandomGenerator(ApplicGare applicGare, NumberConsumer consumer, int declencher) {
        super(500);
        this.applicGare = applicGare;
        this.consumer = consumer;
        this.declencher = declencher;
    }

    @Override
    protected void work() {
        double number = 0 + Math.random() * 1000;
        if(number % declencher == 0) {
            consumer.consumeNumber(number);
        }
    }

}
