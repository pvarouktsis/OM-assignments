package com.oma.algo;

import java.util.List;

public class InsertionMoveType implements MoveType {

    private final AssemblyLine assemblyLine;

    public InsertionMoveType(AssemblyLine as) {
        this.assemblyLine = as;
    }

    @Override
    public boolean improveSolution(Solution sol) {
        double bestTime = assemblyLine.calculateTime(sol);
        for (List<Order> currentMachine : sol) {
            for (int i = 0; i < currentMachine.size(); i++) {
                Order chosenOrder = currentMachine.remove(i);
                for (List<Order> targetMachine : sol) {
                    for (int j = 0; j < targetMachine.size(); j++) {
                        if (targetMachine == currentMachine && i == j) {
                            continue; // Don't put it in the same position it was before
                        }
                        targetMachine.add(j, chosenOrder);
                        double swappedTime = assemblyLine.calculateTime(sol);
                        if (swappedTime < bestTime) {
                            return true; // Found improvement, we are done
                        }
                        targetMachine.remove(j); // Reset the solution to previous state
                    }
                }
                currentMachine.add(i, chosenOrder);
            }
        }
        return false;
    }

}
