package com.oma.algo;

import java.util.ArrayList;
import java.util.Random;

public class OrderGenerator {
  private int seed = 19092000;
  private int maxOrders = 100;
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
}

