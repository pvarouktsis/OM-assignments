package com.oma.algo;

import java.util.List;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        AssemblyLine colorFactory = new AssemblyLine();
        Solution sln = colorFactory.createInitialSolution();
        Algorithm algo1 = new StrategicAlgorithm(colorFactory);
        sln = algo1.compute(sln);
        Algorithm algo2 = new SteepestDescentAlgorithm(colorFactory);
        sln = algo2.compute(sln);
        Algorithm algo3 = new VNDAlgorithm(colorFactory);
        sln = algo3.compute(sln);
        printSolution(sln);
        System.out.println("\nObjective function: " + colorFactory.calculateTime(sln));
    }

    public static void printSolution(Solution sol) {
        Iterator<List<Order>> it = sol.iterator();
        int machineCounter = 1;

        System.out.println("\nSolution: ");
        while (it.hasNext()) {
            List<Order> machine = it.next();

            // if machine is empty
            if (machine.size() == 0) {
                System.out.println("Machine " + machineCounter++ + ": {}");
                continue;
            }

            // otherwise
            System.out.print("Machine " + machineCounter++ + ": {");
            for (int i = 0; i < machine.size() - 1; i++) {
                System.out.print(machine.get(i).getId() + ", ");
            }
            System.out.println(machine.get(machine.size() - 1).getId() + "}");
        }
    }
}
