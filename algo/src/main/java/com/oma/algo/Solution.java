package com.oma.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a set that contains many arrays/lists of elements
 */
public class Solution implements Iterable<List<Order>> {
    public HashSet<List<Order>> set;
 
    public Solution() {
        this.set = new HashSet<List<Order>>();
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

