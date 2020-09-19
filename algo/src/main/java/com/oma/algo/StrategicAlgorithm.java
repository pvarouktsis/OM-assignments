package com.oma.algo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class StrategicAlgorithm implements Algorithm {
    private static final double INF = Double.POSITIVE_INFINITY;
    private AssemblyLine assemblyLine;
    private List<Order> inSolution;

    public StrategicAlgorithm(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
        this.inSolution = new ArrayList<Order>();
    }

    public Solution compute(Solution sol) {
        while (inSolution.size() != assemblyLine.orders.size()) {
            Iterator<List<Order>> it = sol.iterator();
            List<Order> bmachine = null;
            double btime = INF;
            int bpos = -1;
            while (it.hasNext()) {
                List<Order> machine = it.next();
                double time = INF;
                int pos = -1;
                if (machine.isEmpty()) {
                    pos = findMinProductionTime();
                } else {
                    int lo = machine.get(machine.size() - 1).getId() - 1;
                    pos = findMinTotalTimeOfRow(lo);
                }
                machine.add(assemblyLine.orders.get(pos));
                time = assemblyLine.calculateTime(sol);
                machine.remove(machine.size() - 1);
                if (btime > time) {
                    bpos = pos;
                    btime = time;
                    bmachine = machine;
                }
            }
            inSolution.add(assemblyLine.orders.get(bpos));
            bmachine.add(assemblyLine.orders.get(bpos));
        }

        return sol;
    }

    public int findMinProductionTime() {
        double minTime = INF;
        int minPos = -1;

        for (int c = 0; c < assemblyLine.orders.size(); c++) {
            if (inSolution.contains(assemblyLine.orders.get(c)))
                continue;
            if (minTime > assemblyLine.totalTimes[c][c]) {
                minTime = assemblyLine.totalTimes[c][c];
                minPos = c;
            }
        }

        return minPos;
    }

    public int findMinTotalTimeOfRow(int row) {
        double minTime = INF;
        int minPos = -1;

        for (int c = 0; c < assemblyLine.orders.size(); c++) {
            if (inSolution.contains(assemblyLine.orders.get(c)))
                continue;
            if (minTime > assemblyLine.totalTimes[row][c]) {
                minTime = assemblyLine.totalTimes[row][c];
                minPos = c;
            }
        }

        return minPos;
    }
}
