package DesignPattern.Project.Strategy.Car;

public class WIthoutDriver extends CarDecorator{
    public WIthoutDriver(Car car){
        this.car=car;
        driver=0;
    }

    public double getSpeed(){return car.getSpeed();}
    public double getFuelPrice(){return car.getFuelPrice();}

    public double getExpenditure(){return car.getExpenditure();}

    @Override
    public String getDescription() {
        return car.getDescription()+" without driver";
    }

}
