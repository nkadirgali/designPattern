package DesignPattern.Project.Strategy.Car;

public class TollRoad extends CarDecorator{
    public TollRoad(Car car){
        this.car=car;
        decorator=decorator.substring(0,2)+"2"+decorator.charAt(3);
        road=10;
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure();}

    @Override
    public String getDescription() {
        return car.getDescription()+" with toll road";
    }
}
