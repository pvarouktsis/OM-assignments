package com.oma.algo;

public interface Algorithm {
    // Receives current solution (can be empty) and outputs a solution.
    public Solution compute(Solution Solution);
}