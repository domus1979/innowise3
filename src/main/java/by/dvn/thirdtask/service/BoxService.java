package by.dvn.thirdtask.service;

import by.dvn.thirdtask.entity.Box;
import by.dvn.thirdtask.entity.BusDepot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BoxService implements Runnable {
    private static final Logger log = LogManager.getLogger();
    private static final int FUEL_VOLUME = 500;
    private static final int SERVICE_TIME_PERIOD = 5;

    public BoxService() {
    }

    @Override
    public void run() {
        BusDepot busDepot = BusDepot.getInstance();
        List<Box> boxes = busDepot.getBoxes();
        log.info("Start box service.");

        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (Box box : boxes) {
                    int fuel = Integer.min(box.getFuelAmount() + FUEL_VOLUME, box.getMaxFuelAmount());
                    box.setFuelAmount(fuel);
                    log.info("Refuel box N " + box.getNumber());
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(SERVICE_TIME_PERIOD));
            }
        } catch (InterruptedException e) {
            log.error("Stop box service! " + e.getMessage());
        }

        log.info("Stop box service.");

    }
}
