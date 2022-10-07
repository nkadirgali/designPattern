package DesignPattern.Project.Strategy.Car;

import DesignPattern.Project.Interfaces.FuelConsumptionStrategy;

public class CarMoneyCons implements FuelConsumptionStrategy {

    Car car = new Car();
    CarDisplacement carDisplacement = new CarDisplacement();

    public void setCar(Car car) {
        this.car = car;
        carDisplacement.setCar(car);
    }

    @Override
    public double money(double distance) {
        return car.getExpenditure()*distance*car.getFuelPrice()
                +carDisplacement.displacement(distance)*car.getRoad()
                +carDisplacement.displacement(distance)*car.getDriver();
    }
}
