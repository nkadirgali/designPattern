package DesignPattern.Project.Strategy.Car;

public class WithDriver extends CarDecorator{
    public WithDriver(Car car){
        this.car=car;
        decorator=decorator.substring(0,3)+"2";
        driver=1000;
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure();}

    @Override
    public String getDescription() {
        return car.getDescription()+" with driver";
    }

}
