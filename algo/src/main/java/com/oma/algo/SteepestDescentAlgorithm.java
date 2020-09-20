package com.oma.algo;

public class SteepestDescentAlgorithm implements Algorithm {

    private final AssemblyLine assemblyLine;

    public SteepestDescentAlgorithm(AssemblyLine as) {
        this.assemblyLine = as;
    }

    public Solution compute(Solution s) {
        MoveType moveType = new SwapMoveType(assemblyLine);
        int counter = 0;
        while(moveType.improveSolution(s)) {
            counter++;
        }
        System.out.println("\n Swaps until minimum: " + counter);
        return s;
    }
}

