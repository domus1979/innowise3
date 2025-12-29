package by.dvn.thirdtask;

import by.dvn.thirdtask.service.BoxService;
import by.dvn.thirdtask.entity.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Start work.");

        BoxService boxService = new BoxService();
        Thread threadBoxService = new Thread(boxService);
        threadBoxService.start();

        List<Bus> busList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Bus bus = new Bus(i, 60);
            busList.add(bus);
        }

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(busList.get(i));
            threadList.add(t);
            t.start();
        }

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Thread t : threadList) {
            t.interrupt();
        }
        threadBoxService.interrupt();

        log.info("Stop work.");

    }
}