package com.oma.algo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class AssemblyLine {
    private final int MACHINES = 5;
    private final int SEED = 19092000;
    private final int MAX_ORDERS = 100;
    ArrayList<Order> orders;
    double[][] transitionTimes;
    double[][] totalTimes;

    public AssemblyLine() {
        OrderGenerator og = new OrderGenerator(SEED, MAX_ORDERS);

        orders = og.generateOrders();
        transitionTimes = og.generateTransitionTimesOfOrders();
        totalTimes = og.calculateTotalTimesOfOrders(orders, transitionTimes);
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
        double maxTime = 0.0;
        Iterator<List<Order>> iterator = solution.iterator();

        while (iterator.hasNext()) {
            List<Order> machine = iterator.next();
            double tempSum = 0.0;
            // if machine does NOT contain orders, continue
            if (machine.size() == 0)
                continue;
            for (int c = 0; c < machine.size() - 1; c++) {
                int i = machine.get(c).getId() - 1;
                int j = machine.get(c + 1).getId() - 1;
                tempSum += totalTimes[i][j];
            }
            // add the production time of the last order
            int lo = machine.get(machine.size() - 1).getId() - 1;
            tempSum += totalTimes[lo][lo];
            if (maxTime < tempSum)
                maxTime = tempSum;
        }

        return maxTime;
    }
}

