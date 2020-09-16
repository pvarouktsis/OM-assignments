package com.oma.algo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class StrategicAlgorithm implements Algorithm {
    private static final double INF = Double.POSITIVE_INFINITY;
    private AssemblyLine colorFactory;
    private List<Order> orders;
    private List<Order> inSolution;
    private double[][] totalTimes;

    public StrategicAlgorithm() {
        this.colorFactory = new AssemblyLine();
        this.orders = colorFactory.orders;
        this.inSolution = new ArrayList<Order>();
        this.totalTimes = colorFactory.totalTimes;
    }

    public Solution compute(Solution sol) {

        // if solution is an empty set, return
        if (sol.isEmpty())
            return sol;

        // process
        while (inSolution.size() != orders.size()) {
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
                machine.add(orders.get(pos));
                time = colorFactory.calculateTime(sol);
                machine.remove(machine.size() - 1);
                if (btime > time) {
                    bpos = pos;
                    btime = time;
                    bmachine = machine;
                }
            }

            inSolution.add(orders.get(bpos));
            bmachine.add(orders.get(bpos));
        }

        return sol;
    }

    public int findMinProductionTime() {
        double minTime = INF;
        int minPos = -1;

        for (int c = 0; c < orders.size(); c++) {
            if (inSolution.contains(orders.get(c)))
                continue;
            if (minTime > totalTimes[c][c]) {
                minTime = totalTimes[c][c];
                minPos = c;
            }
        }

        return minPos;
    }

    public int findMinTotalTimeOfRow(int row) {
        double minTime = INF;
        int minPos = -1;

        for (int c = 0; c < orders.size(); c++) {
            if (inSolution.contains(orders.get(c)) || row == c)
                continue;
            if (minTime > totalTimes[row][c]) {
                minTime = totalTimes[row][c];
                minPos = c;
            }
        }

        return minPos;
    }
}
