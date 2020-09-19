package com.oma.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a set that contains many arrays/lists of elements
 */
public class Solution implements Iterable<List<Order>> {
    public List<List<Order>> set;

    public Solution() {
        this.set = new ArrayList<List<Order>>();
    }

    public Solution(Solution other) {
        this();
        for (List<Order> list : other) {
            ArrayList<Order> newList = new ArrayList<Order>(list.size());
            for (Order order : list) {
                newList.add(order);
            }
            this.set.add(newList);
        }
    }

    @Override
    public Iterator<List<Order>> iterator() {
        return set.iterator();
    }

    // Create new array to solution set
    public void addArray() {
        set.add(new ArrayList<Order>());
    }
}
