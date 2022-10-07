package DesignPattern.Project.Strategy.Car;

public class Hatchback extends CarDecorator{
    public Hatchback(Car car){
        this.car=car;
        decorator="2"+decorator.substring(1);
    }

    public double getSpeed(){return car.getSpeed()+10;}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure()-0.03;}

    @Override
    public String getDescription() {
        return car.getDescription()+" with body type Hatchback";
    }
}
