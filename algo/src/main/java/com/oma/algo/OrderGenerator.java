package com.oma.algo;

import java.util.ArrayList;
import java.util.Random;

public class OrderGenerator {
  private static final int SEED = 19092000;
  private static final int MAX_ORDERS = 100;

  public ArrayList<Order> generateOrders() {
    ArrayList<Order> orders = new ArrayList<>();
    Random rg = new Random(SEED);

    for (int i = 0; i < MAX_ORDERS; i++) {
      int quantity = 100 + rg.nextInt(401);
      boolean dark = false;
      if (rg.nextDouble() < 0.15) {
        dark = true;
      }
      Order o = new Order(i+1, quantity, dark);
      orders.add(o);
    }

    return orders;
  }

}

