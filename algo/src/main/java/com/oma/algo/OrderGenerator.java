package com.oma.algo;

import java.util.ArrayList;
import java.util.Random;

public class OrderGenerator {
    private int seed;
    private int maxOrders;
    private Random rg = new Random(seed);

    public OrderGenerator(int seed, int maxOrders) {
        this.seed = seed;
        this.maxOrders = maxOrders;
    }

    public ArrayList<Order> generateOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        for (int i = 0; i < maxOrders; i++) {
            int id = i + 1;
            int quantity = 100 + rg.nextInt(401);
            boolean dark = false;
            if (rg.nextDouble() < 0.15) {
                dark = true;
            }
            Order o = new Order(id, quantity, dark);
            orders.add(o);
        }

        return orders;
    }

    public double[][] generateTransitionTimesOfOrders() {
        double[][] transitionTimes = new double[maxOrders][maxOrders];

        for (int i = 0; i < maxOrders; i++) {
            for (int j = 0; j < maxOrders; j++) {
                double time = 0;
                double random = rg.nextDouble();
                if (i == j) 
                    continue;
                time = Math.round((10 + 20 * random) * 100.0) / 100.0;
                transitionTimes[i][j] = time;
            }
        }

        return transitionTimes;
    }

    public double[][] calculateTotalTimesOfOrders(ArrayList<Order> orders,
            double[][] transitionTimes) {
        // totalTimes is a 2D array which contains the transition time of
        // order i to order j, plus the additional 15 minutes of cleaning
        // the machine (if order i is a dark color and j is a light color),
        // plus the production time of order i
        double[][] totalTimes = new double[maxOrders][maxOrders];

        for (int i = 0; i < maxOrders; i++) {
            // calculate production time of the ith order in minutes
            double productionTime = orders.get(i).getQuantity() / 10; // (quantity * 6) / 60
            for (int j = 0; j < maxOrders; j++) {
                totalTimes[i][j] = transitionTimes[i][j];
                totalTimes[i][j] += productionTime;
                if (orders.get(i).getDark() && !orders.get(j).getDark()) { // dark && light
                    totalTimes[i][j] += 15;
                }
            }
        }

        return totalTimes;
    }
}

