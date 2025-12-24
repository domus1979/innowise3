package by.dvn.thirdtask.entity;

import java.util.concurrent.Callable;

public class Bus implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        BusDepot busDepot = BusDepot.getInstance();

        return 0;
    }
}
