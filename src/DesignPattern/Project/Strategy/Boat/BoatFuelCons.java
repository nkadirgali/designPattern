package DesignPattern.Project.Strategy.Boat;

import DesignPattern.Project.Interfaces.FuelConsumptionStrategy;

public class BoatFuelCons implements FuelConsumptionStrategy {
    @Override
    public double money(double distance){
        double expenditure = 5.0; // на каждый метр (вымышленное ззначение)
        return expenditure*distance;
    }
}
