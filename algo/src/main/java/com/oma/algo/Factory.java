package com.oma.algo;

import java.util.ArrayList;

public class Factory {

    private final int SEED = 19092000;
    private final int MAX_ORDERS = 100;
    ArrayList<Order> orders;
    double[][] transitionTimes;
    
    public Factory() {
        OrderGenerator og = new OrderGenerator(SEED, MAX_ORDERS);

        orders = og.generateOrders();
        transitionTimes = og.generateTransitionTimesOfOrders();

    }

    public void calculateTime(Solution solution) {

    }
}