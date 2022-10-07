package DesignPattern.Project.Strategy.Car;

public class Electricity extends CarDecorator{
    public Electricity(Car car){
        this.car=car;
        decorator=decorator.charAt(0)+"3"+decorator.substring(2);
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice()/2;}

    public double getExpenditure(){return car.getExpenditure()*0.9;}

    @Override
    public String getDescription() {
        return car.getDescription()+" with fuel type Electricity";
    }
}
