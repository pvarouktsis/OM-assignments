package com.oma.algo;

import java.util.List;

public class InsertionMoveType implements MoveType {

    private final AssemblyLine assemblyLine;

    public InsertionMoveType(AssemblyLine as) {
        this.assemblyLine = as;
    }

    @Override
    public boolean improveSolution(Solution sol) {
        for (List<Order> machine : sol) {
            double machineBestTime = assemblyLine.calculateTimeOfMachine(machine);
            for (int i = 0; i < machine.size() - 1; i++) {
                Order chosenOrder = machine.remove(i);
                for (int j = i + 1; j < machine.size(); j++) {
                    machine.add(j, chosenOrder);
                    double swappedTime = assemblyLine.calculateTimeOfMachine(machine);
                    if (swappedTime < machineBestTime) {
                        return true; // Found improvement, we are done
                    }
                    machine.remove(j); // Reset the solution to previous state
                }
                machine.add(i, chosenOrder);
            }
        }
        return false;
    }

}
