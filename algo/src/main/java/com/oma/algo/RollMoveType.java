package com.oma.algo;

import java.util.List;

public class RollMoveType implements MoveType {
    private AssemblyLine assemblyLine;

    public RollMoveType (AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public boolean improveSolution(Solution s) {
        double btime = assemblyLine.calculateTime(s);
        int bcount = -1;

        for (int c = 0; c < assemblyLine.orders.size(); c++) {
            s = rollForwardOrders(s);
            double time = assemblyLine.calculateTime(s);
            if (btime > time) {
                btime = time;
                bcount = c;
            }
        }

        if (bcount != -1) {
            for (int c = assemblyLine.orders.size() - 1; c > bcount; c--) {
                s = rollBackOrders(s);
            }
            return true;
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
}
