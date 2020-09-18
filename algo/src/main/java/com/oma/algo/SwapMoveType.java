package com.oma.algo;

import java.util.List;

public class SwapMoveType implements MoveType {

    private final AssemblyLine assemblyLine;

    public SwapMoveType(AssemblyLine as) {
        this.assemblyLine = as;
    }

    @Override
    public boolean improveSolution(Solution sol) {
        for (List<Order> machine : sol) {
            double machineBestTime = assemblyLine.calculateTimeOfMachine(machine);
            for (int i = 0; i < machine.size() - 1; i++) {
                for (int j = i + 1; j < machine.size(); j++) {
                    swapElements(machine, i, j);
                    double swappedTime = assemblyLine.calculateTimeOfMachine(machine);
                    if (swappedTime < machineBestTime) {
                        return true; // Found improvement, we are done
                    }
                    swapElements(machine, i, j); // Reset the solution to previous state
                }
            }
        }
        return false;
    }

    private void swapElements(List<Order> machine,int idx1,int idx2) {
        Order temp = machine.get(idx1);
        machine.set(idx1, machine.get(idx2));
        machine.set(idx2, temp);
    }
    
}
