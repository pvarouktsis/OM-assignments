package com.oma.algo;

import java.util.ArrayList;

public class Main {
  private static final int SEED = 19092000;
  private static final int MAX_ORDERS = 100;

  public static void main(String[] args) {
    ArrayList<Order> orders = new ArrayList<>();
    double[][] transitionTimes = new double[MAX_ORDERS][MAX_ORDERS];
    OrderGenerator og = new OrderGenerator(SEED, MAX_ORDERS);

    orders = og.generateOrders();
    transitionTimes = og.generateTransitionTimesOfOrders();

    // print orders
    // (id, quantity, dark)
    for (Order order: orders)
      System.out.println(order.toString());

    // print transitionTimes
    // (i, j, transitionTimes[i][j])
    for (int i = 0; i < MAX_ORDERS; i++)
      for (int j = 0; j < MAX_ORDERS; j++)
        System.out.println("(" + i + ", " + j + ", " + transitionTimes[i][j] + ")");
  }

}

