package com.oma.algo;

public class Order {
  private int id;
  private double quantity;
  private boolean dark;

  public Order(int id, double quantity, boolean dark) {
    this.id = id;
    this.quantity = quantity;
    this.dark = dark;
  }

  public String toString() {
    return "(" + this.id + ", " + this.quantity + ", " + this.dark + ")";
  }

}

