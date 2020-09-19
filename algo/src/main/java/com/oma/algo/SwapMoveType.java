package com.oma.algo;

import java.util.Iterator;
import java.util.List;

public class SwapMoveType implements MoveType {

    private final AssemblyLine assemblyLine;

    public SwapMoveType(AssemblyLine as) {
        this.assemblyLine = as;
    }

    @Override
    public boolean improveSolution(Solution sol) {
        double bestTime = assemblyLine.calculateTime(sol);
        for (List<Order> currentMachine : sol) {
            for (int i = 0; i < currentMachine.size(); i++) {
                Iterator<List<Order>> targetIt = sol.iterator();
                while (targetIt.next() != currentMachine); // Move iterator to the same position
                for (List<Order> targetMachine = currentMachine; targetIt.hasNext(); targetMachine = targetIt.next()) {
                    int j = 0;
                    if (currentMachine == targetMachine) {
                        j = i + 1; // Begin swapping with the next order
                    }
                    for (; j < targetMachine.size(); j++) {
                        swapElements(currentMachine, i, targetMachine, j);
                        double swappedTime = assemblyLine.calculateTime(sol);
                        if (swappedTime < bestTime) {
                            return true; // Found improvement, we are done
                        }
                        swapElements(currentMachine, i, targetMachine, j); // Reset the solution to previous state
                    }
                }
            }
        }
        return false;
    }

    private void swapElements(List<Order> machine, int idx1, List<Order> machine2, int idx2) {
        Order temp = machine.get(idx1);
        machine.set(idx1, machine2.get(idx2));
        machine2.set(idx2, temp);
    }
    
}
