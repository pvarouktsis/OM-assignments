package com.oma.algo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      ArrayList<Order> orders = new ArrayList<>();
      OrderGenerator og = new OrderGenerator();
      orders = og.generateOrders();
  
      for (Order order: orders)
        System.out.println(order.toString());
    }

}

