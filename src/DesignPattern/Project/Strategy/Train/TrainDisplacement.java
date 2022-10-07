package DesignPattern.Project.Strategy.Train;

import DesignPattern.Project.Interfaces.DisplacementStrategy;

public class TrainDisplacement implements DisplacementStrategy {
    @Override
    public double displacement(double distance){
        double speed = 70.0;
        return (distance/speed);
    }
}
