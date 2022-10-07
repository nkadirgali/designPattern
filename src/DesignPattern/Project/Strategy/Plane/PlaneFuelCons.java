package DesignPattern.Project.Strategy.Plane;

import DesignPattern.Project.Interfaces.FuelConsumptionStrategy;

public class PlaneFuelCons implements FuelConsumptionStrategy {
    @Override
    public double money(double distance){
        double expenditure = 0.0473; //на каждый метр, (100km - 4,73) for one passenger
        return expenditure*distance;
    }
}
