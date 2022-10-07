package DesignPattern.Project.Strategy.Car;

public class SportCar extends CarDecorator{
    public SportCar(Car car){
        this.car=car;
        decorator="4"+decorator.substring(1);
    }

    public double getSpeed(){return car.getSpeed()+90;}
    public double getFuelPrice(){return car.getFuelPrice();}
    public double getExpenditure(){return car.getExpenditure()+0.1;}

    @Override
    public String getDescription() {
        return car.getDescription()+" with body type SportCar";
    }
}
