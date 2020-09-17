package com.oma.algo;

public class Main {

    public static void main(String[] args) {
        AssemblyLine colorFactory = new AssemblyLine();
        Solution sln = colorFactory.createInitialSolution();
        Algorithm algo1 = new StrategicAlgorithm();
        sln = algo1.compute(sln);
        Algorithm algo2 = new SteepestDescentAlgorithm();
        sln = algo2.compute(sln);
        Algorithm algo3 = new VNDAlgorithm(colorFactory);
        sln = algo3.compute(sln);
        printSolution(sln);
    }

    public static void printSolution(Solution sln) {
        System.out.println("Hello, World!");
    }
}

