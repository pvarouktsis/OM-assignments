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

    @Override
    public Iterator<List<Order>> iterator() {
        return set.iterator();
    }

    // Create new array to solution set
    public void addArray() {
        set.add(new ArrayList<Order>());
    }

    // Return if solution is empty
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
