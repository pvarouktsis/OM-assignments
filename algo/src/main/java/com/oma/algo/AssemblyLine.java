package com.oma.algo;

import java.util.ArrayList;

public class AssemblyLine {
    private final int MACHINES = 5;
    private final int SEED = 19092000;
    private final int MAX_ORDERS = 100;
    ArrayList<Order> orders;
    double[][] transitionTimes;
    
    public AssemblyLine() {
        OrderGenerator og = new OrderGenerator(SEED, MAX_ORDERS);

        orders = og.generateOrders();
        transitionTimes = og.generateTransitionTimesOfOrders();
    }

    public Solution createInitialSolution() {
        Solution s = new Solution();
        for (int i = 0; i < MACHINES; i++) {
            s.addArray();
        }
        return s;
    }

    // Calculate objective function
    public double calculateTime(Solution solution) {
        return 0.0;
    }
}

