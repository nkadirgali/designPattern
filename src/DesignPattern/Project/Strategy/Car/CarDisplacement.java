package DesignPattern.Project.Strategy.Car;

import DesignPattern.Project.Interfaces.DisplacementStrategy;

public class CarDisplacement implements DisplacementStrategy {

    Car car = new Car();

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public double displacement(double distance){
        return (distance/ car.getSpeed());
    }
}
