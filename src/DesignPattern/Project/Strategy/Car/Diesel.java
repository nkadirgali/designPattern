package DesignPattern.Project.Strategy.Car;

public class Diesel extends CarDecorator{
    public Diesel(Car car){
        this.car=car;
        decorator=decorator.charAt(0)+"2"+decorator.substring(2);
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice()*2;}

    public double getExpenditure(){return car.getExpenditure()*0.8;}

    @Override
    public String getDescription() {
        return car.getDescription()+" with fuel type Diesel";
    }
}
