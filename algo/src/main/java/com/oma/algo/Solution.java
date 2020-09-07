package com.oma.algo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Solution implements Iterable<List<Order>> {
    public HashSet<List<Order>> machines;
    
    public Solution() {
        this.machines = new HashSet<List<Order>>();
    }

    @Override
    public Iterator<List<Order>> iterator() {
        return machines.iterator();
    };
    
}