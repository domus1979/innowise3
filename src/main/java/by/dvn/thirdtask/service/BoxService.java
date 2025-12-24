package by.dvn.thirdtask.service;

import by.dvn.thirdtask.entity.Box;
import by.dvn.thirdtask.entity.BusDepot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BoxService implements Runnable {
    private static final Logger log = LogManager.getLogger();
    private boolean stopBoxService = false;
    private ReentrantLock lock;
    private Box box;

    public BoxService(Box box, ReentrantLock lock) {
        this.lock = lock;
        this.box = box;
    }

    public void stopBoxService() {
        this.stopBoxService = true;
    }

    @Override
    public void run() {
        BusDepot busDepot = BusDepot.getInstance();
        List<Box> boxes = busDepot.getBoxes();

        while (true) {

            for (Box box : boxes) {
                if (box.isAvailable()) {
                    lock.lock();
                    try {
                        box.setAvailable(false);
                        box.setFuelAmount(box.getFuelAmount() + 10);
                        box.setPartsNumber(box.getPartsNumber() + 5);
                        box.setAvailable(true);
                    } finally {
                        lock.unlock();
                    }
                }
            }

            try {
                wait(TimeUnit.SECONDS.toMillis(5));
            } catch (InterruptedException e) {
                log.error("Can`t wait for service box.");
            }

            if (this.stopBoxService) {
                break;
            }
        }

    }
}
