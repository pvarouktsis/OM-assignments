package com.oma.algo;

public class SteepestDescentAlgorithm implements Algorithm {

    private final AssemblyLine assemblyLine;

    public SteepestDescentAlgorithm(AssemblyLine as) {
        this.assemblyLine = as;
    }

    public Solution compute(Solution s) {
        MoveType moveType = new SwapMoveType(assemblyLine);
        while(moveType.improveSolution(s));
        return s;
    }
}

