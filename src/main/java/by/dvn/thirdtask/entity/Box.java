package by.dvn.thirdtask.entity;

import java.util.concurrent.locks.ReentrantLock;

public class Box {
    private ReentrantLock lock = new ReentrantLock();
    private boolean isAvailable;
    private Integer partsNumber;
    private Integer fuelAmount;

    public Box() {
        this.isAvailable = true;
        this.partsNumber = 0;
        this.fuelAmount = 0;
    }

    public Integer getPartsNumber() {
        return partsNumber;
    }

    public void setPartsNumber(Integer partsNumber) {
        this.partsNumber = partsNumber;
    }

    public Integer getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Integer fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean state) {
        isAvailable = state;
    }
}
