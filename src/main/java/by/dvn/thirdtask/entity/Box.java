package by.dvn.thirdtask.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Box {
    private static final Logger log = LogManager.getLogger();
    private static final int MIN_FUEL_AMOUNT = 100;
//    private static final int SERVICE_TIME = 2;
    private ReentrantLock locker = new ReentrantLock();
    private final int maxFuelAmount;
    private int number;
    private boolean avalibleState;
    private Integer fuelAmount;

    public Box(int number, int maxFuelAmount) {
        this.maxFuelAmount = maxFuelAmount;
        this.number = number;
        avalibleState = true;
        this.fuelAmount = 0;
    }

    public int getMaxFuelAmount() {
        return maxFuelAmount;
    }

    public Integer getFuelAmount() {
        return fuelAmount;
    }

    public int getNumber() {
        return number;
    }

    public void setFuelAmount(Integer fuelAmount) {
        locker.lock();
        try {
            this.fuelAmount = fuelAmount;
        } finally {
            locker.unlock();
        }
    }

    public boolean isAvailable() {
        return avalibleState && fuelAmount >= MIN_FUEL_AMOUNT;
    }

    public boolean ServiceBus(Bus bus) {
        locker.lock();
        boolean res = false;
        try {
            avalibleState = false;
            log.info("Bus N " + bus.getNumber() + " occupied box " + this.number);
            int needFuel = bus.getTankVolume() - bus.getFuelAmount();
            if (needFuel <= fuelAmount) {
//                Thread.sleep(TimeUnit.SECONDS.toMillis(SERVICE_TIME));
                setFuelAmount(fuelAmount - needFuel);
                log.info("Bus N " + bus.getNumber() + " serviced in the box " + this.number);
                res = true;
            }
//        } catch (InterruptedException e) {
//            log.error("Bus N " + bus.getNumber() + " not serviced in the box " + this.number + ".\n" + e.getMessage());
        } finally {
            avalibleState = true;
            locker.unlock();
        }
        return res;
    }
}
