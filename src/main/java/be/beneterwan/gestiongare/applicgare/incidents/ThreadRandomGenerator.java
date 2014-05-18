package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ThreadRandomGenerator extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(ThreadRandomGenerator.class);

    private final NumberConsumer consumer;
    private final int declencher;

    public ThreadRandomGenerator(NumberConsumer consumer, int declencher) {
        super(500);
        this.consumer = consumer;
        this.declencher = declencher;
    }

    @Override
    protected void work() {
        int number = (int) (0 + Math.random() * 1000);
        if(number % declencher == 0) {
            LOGGER.fine("Stuff happened \\o/");
            consumer.consumeNumber(number);
        }
    }

}
