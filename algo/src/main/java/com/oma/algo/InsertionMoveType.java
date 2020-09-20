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
        List<Order> machineToRemove = null, machineToAdd = null;
        boolean improved = false;
        int idxToRemove = 0, idxToAdd = 0;
        for (List<Order> currentMachine : sol) {
            for (int i = 0; i < currentMachine.size(); i++) {
                Order chosenOrder = currentMachine.remove(i);
                for (List<Order> targetMachine : sol) {
                    for (int j = 0; j < targetMachine.size(); j++) {
                        if (targetMachine == currentMachine && i == j) {
                            continue; // Don't put it in the same position it was before
                        }
                        targetMachine.add(j, chosenOrder);
                        double newTime = assemblyLine.calculateTime(sol);
                        if (newTime < bestTime) {
                            bestTime = newTime;
                            improved = true;
                            machineToRemove = currentMachine;
                            machineToAdd = targetMachine;
                            idxToRemove = i;
                            idxToAdd = j;
                        }
                        targetMachine.remove(j); // Reset the solution to previous state
                    }
                }
                currentMachine.add(i, chosenOrder);
            }
        }
        if (improved) {
            Order chosenOrder = machineToRemove.remove(idxToRemove);
            machineToAdd.add(idxToAdd, chosenOrder);
            return true;
        }
        return false;
    }

}
