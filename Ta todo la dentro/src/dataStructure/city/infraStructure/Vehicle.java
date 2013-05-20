package dataStructure.city.infraStructure;

public class Vehicle {
    
    
    private double currentFuel;
    private double maxCapacity;
    private double consumption;

    public Vehicle(Vehicle car) {
            currentFuel = car.getCurrentFuel();
            maxCapacity = car.getMaxCapacity();
            consumption = car.getConsumption();
    }
    
    public Vehicle(double start, double max){
            if(start > max){
                    currentFuel = max;
            } else {
                    currentFuel = start;
            }
            maxCapacity = max;
            consumption = 1;
    }
    
    public Vehicle(double start, double max, double cons){
            if(start > max){
                    currentFuel = max;
            } else {
                    currentFuel = start;
            }
            maxCapacity = max;
            consumption = cons;
    }

    public double getCurrentFuel(){
            return currentFuel;
    }
    
    public double getMaxCapacity(){
            return maxCapacity;
    }
    
    public double getConsumption() {
            return consumption;
    }
    
    public void setCurrentFuel(int fuel) {
            if(fuel > maxCapacity){
                    currentFuel = maxCapacity;
            } else {
                    currentFuel = fuel;
            }
    }
    
    public void travel(double distance) {
            currentFuel -= distance*consumption;
    }
    
    public void refill(){
            currentFuel = maxCapacity;
    }
    
    public void setMaxCapacity(double cap){
            maxCapacity = cap;
    }

    public void setConsumption(double cons) {
            consumption = cons;
    }
    
}