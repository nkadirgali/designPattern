package DesignPattern.Project.Strategy.Plane;
import DesignPattern.Project.Interfaces.DisplacementStrategy;

public class PlaneDisplacement implements DisplacementStrategy {
    @Override
    public double displacement(double distance){
        double speed = 800;
        return (distance/speed);
    }
}
