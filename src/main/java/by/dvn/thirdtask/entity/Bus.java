package by.dvn.thirdtask.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bus implements Runnable {
    private static final Logger log = LogManager.getLogger();
    private static final int SERVICE_TIME = 1;
    private static final int MIN_FUEL_AMOUNT = 10;
    private final int tankVolume;
    private int number;
    private int fuelAmount;

    public Bus(int number, int tankVolume) {
        this.number = number;
        this.tankVolume = tankVolume;
        this.fuelAmount = MIN_FUEL_AMOUNT;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public int getTankVolume() {
        return tankVolume;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        log.info("Start bus N " + this.number);

        BusDepot busDepot = BusDepot.getInstance();
        List<Box> boxes = busDepot.getBoxes();

        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (fuelAmount <= MIN_FUEL_AMOUNT) {
                    for (Box box : boxes) {
                        if (box.isAvailable()) {
                            if (box.ServiceBus(this)) {
                                Thread.sleep(TimeUnit.SECONDS.toMillis(SERVICE_TIME));
                                fuelAmount = tankVolume;
                                log.info("Refuel bus N " + this.number);
                            }
                            break;
                        }
                    }
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                if (fuelAmount >= MIN_FUEL_AMOUNT) {
                    fuelAmount -= MIN_FUEL_AMOUNT;
                    log.info("Bus N " + this.number + " have fuel = " + fuelAmount);
                }
            }
        } catch (InterruptedException e) {
            log.error("Stop bus N " + this.number + ". " + e.getMessage());
        }

        log.info("Stop bus N " + this.number);
    }
}
