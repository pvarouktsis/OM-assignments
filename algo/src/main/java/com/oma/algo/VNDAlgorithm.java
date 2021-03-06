package com.oma.algo;

public class VNDAlgorithm implements Algorithm {

    private final AssemblyLine assemblyLine;

    public VNDAlgorithm(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public Solution compute(Solution s) {
        MoveType moveTypes[] = { new RollMoveType(assemblyLine), new SwapMoveType(assemblyLine),
                new InsertionMoveType(assemblyLine) };
        int i = 0;
        while (i < moveTypes.length) {
            MoveType current = moveTypes[i];
            if (!current.improveSolution(s)) {
                i++;
            } else {
                i = 0;
            }
        }
        return s;
    }
}
