package com.oma.algo;

import java.util.List;

public class RollMoveType implements MoveType {
    private static final int INF = Integer.MAX_VALUE;
    private AssemblyLine assemblyLine;

    public RollMoveType (AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public boolean improveSolution(Solution s) {
        int minSize = findMachineWithMinSize(s);
        double btime = assemblyLine.calculateTime(s);

        for (int c = 0; c < minSize; c++) {
            s = rollForwardOrders(s);
            double time = assemblyLine.calculateTime(s);
            if (btime > time) {
                return true;
            }
        }

        for (int c = 0; c < minSize; c++) {
            s = rollBackOrders(s);
        }

        return false;
    }

    public Solution rollForwardOrders(Solution s) {
        Order prevOrder = null;

        for (List<Order> machine : s) {
            if (prevOrder == null) {
                prevOrder = machine.remove(machine.size() - 1);
                continue;
            }
            machine.add(0, prevOrder);
            prevOrder = machine.remove(machine.size() - 1);
        }
        List<Order> machine = s.iterator().next();
        machine.add(0, prevOrder);
        
        return s;
    }

    public Solution rollBackOrders(Solution s) {
        Order firstOrder = null;
        List<Order> prevMachine = null;

        for (List<Order> machine : s) {
            if (firstOrder == null) {
                firstOrder = machine.remove(0);
                prevMachine = machine;
                continue;
            }
            prevMachine.add(machine.remove(0));
            prevMachine = machine;
        }
        prevMachine.add(firstOrder);

        return s;
    }

    public int findMachineWithMinSize(Solution s) {
        int minSize = INF;
        
        for (List<Order> machine : s) {
            int size = machine.size();
            if (minSize > size)
                minSize = size;
        }

        return minSize;
    }
}
