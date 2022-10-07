package DesignPattern.Project.Strategy.Car;

public class FreeRoad extends CarDecorator{
    public FreeRoad(Car car){
        this.car=car;
        road=0;
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure();}

    @Override
    public String getDescription() {
        return car.getDescription()+" with free road";
    }
}
