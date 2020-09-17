package com.oma.algo;

public interface MoveType {

    // Return false, if solution could not improve 
    public boolean improveSolution(Solution sol);
}
