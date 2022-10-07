package DesignPattern.Project.Strategy.Car;

public class DefaultFuel extends CarDecorator{
    public DefaultFuel(Car car){this.car=car;}

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure();}

    @Override
    public String getDescription() {
        return car.getDescription();
    }
}
