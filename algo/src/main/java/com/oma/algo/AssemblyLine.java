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

    // Create 5 machines
    public Solution createInitialSolution() {
        Solution s = new Solution();
        for (int i = 0; i < MACHINES; i++) {
            s.addArray();
        }
        return s;
    }

    // Calculate objective function
    public double calculateTime(Solution sol) {
        double maxTime = 0.0;
        Iterator<List<Order>> iterator = sol.iterator();

        // process
        while (iterator.hasNext()) {
            List<Order> machine = iterator.next();
            double machineTime = calculateTimeOfMachine(machine);
            if (maxTime < machineTime)
                maxTime = machineTime;
        }

        return maxTime;
    }

    // Calculate the time of a machine
    public double calculateTimeOfMachine(List<Order> machine) {
        double machineTime = 0.0;

        // if machine does NOT contain orders, return 0.0
        if (machine.isEmpty())
            return machineTime;

        // process
        for (int c = 0; c < machine.size() - 1; c++) {
            int i = machine.get(c).getId() - 1;
            int j = machine.get(c + 1).getId() - 1;
            machineTime += totalTimes[i][j];
        }
        // add the production time of the last order
        int lo = machine.get(machine.size() - 1).getId() - 1;
        machineTime += totalTimes[lo][lo];

        return machineTime;
    }

    public Solution createBasicSolution() {
        Solution s = createInitialSolution();
        int i = 0;
        while (i < orders.size()) {
            for (List<Order> machine : s) {
                machine.add(orders.get(i));
                i++;
            }
        }
        return s;
    }
}
