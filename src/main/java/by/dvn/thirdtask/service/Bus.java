package by.dvn.thirdtask.service;

import by.dvn.thirdtask.entity.Box;
import by.dvn.thirdtask.entity.BusDepot;

import java.util.List;

public class Bus implements Runnable {

    @Override
    public void run() {

        BusDepot busDepot = BusDepot.getInstance();
        List<Box> boxes = busDepot.getBoxes();

        for (Box box : boxes) {
            if (box.isAvailable()) {

            }
        }

    }
}
