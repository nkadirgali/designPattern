package DesignPattern.Project.Strategy.Boat;

import DesignPattern.Project.Interfaces.DisplacementStrategy;

public class BoatDisplacement implements DisplacementStrategy {
    @Override
    public double displacement(double distance){
        double speed = 30.0;
        return (distance/speed);
    }
}
