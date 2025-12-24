package by.dvn.thirdtask.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BusDepot {
    private static final Logger log = LogManager.getLogger();
    private static final Integer BOX_NUMBER = 4;
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
        log.info("Create bus depot.");
        return instance;
    }

    private BusDepot() {
        for (int i = 0; i < BOX_NUMBER; i++) {
            boxes.add(new Box());
        }
    }

    public List<Box> getBoxes() {
        return boxes;
    }

}
