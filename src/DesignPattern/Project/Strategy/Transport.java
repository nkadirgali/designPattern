package DesignPattern.Project.Strategy;

import DesignPattern.Project.Interfaces.DisplacementStrategy;
import DesignPattern.Project.Interfaces.FuelConsumptionStrategy;
import DesignPattern.Project.Strategy.Boat.BoatDisplacement;
import DesignPattern.Project.Strategy.Boat.BoatFuelCons;
import DesignPattern.Project.Strategy.Car.Car;
import DesignPattern.Project.Strategy.Car.CarDisplacement;
import DesignPattern.Project.Strategy.Car.CarMoneyCons;
import DesignPattern.Project.Strategy.Plane.PlaneDisplacement;
import DesignPattern.Project.Strategy.Plane.PlaneFuelCons;
import DesignPattern.Project.Strategy.Train.TrainDisplacement;
import DesignPattern.Project.Strategy.Train.TrainFuelCons;

public class Transport{

   private FuelConsumptionStrategy fuelConsumptionStrategy;
   private DisplacementStrategy displacementStrategy;

   CarMoneyCons carFuelCons = new CarMoneyCons();
   CarDisplacement carDisplacement = new CarDisplacement();

    public Transport(){
        fuelConsumptionStrategy = carFuelCons; //default
        displacementStrategy = carDisplacement; //default
    }

    public FuelConsumptionStrategy fuelConsumptionStrategy(){return fuelConsumptionStrategy;}

    public double usageFuel(double distance){
        return fuelConsumptionStrategy.money(distance);
    }

    public double displacement(double distance){
        return displacementStrategy.displacement(distance);
    }

    //choosing transport

    public void setCar(Car car){
        carFuelCons.setCar(car);
        carDisplacement.setCar(car);
        fuelConsumptionStrategy = carFuelCons;
        displacementStrategy = carDisplacement;
    }

    public void setPlane(){
        fuelConsumptionStrategy = new PlaneFuelCons();
        displacementStrategy = new PlaneDisplacement();
    }

    public void setTrain(){
        fuelConsumptionStrategy = new TrainFuelCons();
        displacementStrategy = new TrainDisplacement();
    }

    public void setBoat(){
        fuelConsumptionStrategy = new BoatFuelCons();
        displacementStrategy = new BoatDisplacement();
    }

}
