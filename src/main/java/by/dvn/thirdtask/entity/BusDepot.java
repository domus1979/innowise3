package by.dvn.thirdtask.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BusDepot {
    private static final Logger log = LogManager.getLogger();
    private static final int BOX_NUMBER = 2;
    private static final int[] BOX_FUEL_AMOUNT = {1200, 500};
    private static final ReentrantLock locker = new ReentrantLock();
    private static BusDepot instance;
    private List<Box> boxes = new ArrayList<>();

    public static BusDepot getInstance() {
        if (instance == null) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new BusDepot();
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    private BusDepot() {
        for (int i = 0; i < BOX_NUMBER; i++) {
            boxes.add(new Box(i, BOX_FUEL_AMOUNT[i]));
        }
        log.info("Create bus depot.");
    }

    public List<Box> getBoxes() {
        return boxes;
    }

}
