package DesignPattern.Project.Strategy.Car;

public class SUV extends CarDecorator{
    public SUV(Car car){
        this.car=car;
        decorator="3"+decorator.substring(1);
    }

    public double getSpeed(){return car.getSpeed()-5;}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure()+0.07;}

    @Override
    public String getDescription() {
        return car.getDescription()+" with body type SUV";
    }
}
