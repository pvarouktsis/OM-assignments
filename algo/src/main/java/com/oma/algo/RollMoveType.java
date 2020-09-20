package com.oma.algo;

import java.util.List;

public class RollMoveType implements MoveType {
    private AssemblyLine assemblyLine;

    public RollMoveType (AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public boolean improveSolution(Solution s) {
        double btime = assemblyLine.calculateTime(s);

        for (int c = 0; c < assemblyLine.orders.size(); c++) {
            s = rollOrders(s);
            double time = assemblyLine.calculateTime(s);
            if (btime > time) {
                return true;
            }
        }

        return false;
    }

    public Solution rollOrders(Solution s) {
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
}
