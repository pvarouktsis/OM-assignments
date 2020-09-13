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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return this.id;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean getDark() {
        return this.dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String toString() {
        return "(" + this.id + ", " + this.quantity + ", " + this.dark + ")";
    }
}

