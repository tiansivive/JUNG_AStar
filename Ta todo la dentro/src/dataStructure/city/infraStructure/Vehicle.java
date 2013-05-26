package dataStructure.city.infraStructure;

public class Vehicle {


	private double currentFuel;
	private double maxCapacity;
	private double consumption;
	private int maxSpeed;

	public Vehicle(Vehicle car) {
		currentFuel = car.getCurrentFuel();
		maxCapacity = car.getMaxCapacity();
		consumption = car.getConsumption();
		if(car.getMaxSpeed() != 0) {
			maxSpeed = car.getMaxSpeed();
		} else {
			maxSpeed = 1;
		}
	}

	public Vehicle(double start, double max){
		if(start > max){
			currentFuel = max;
		} else {
			currentFuel = start;
		}
		maxCapacity = max;
		consumption = 1;
		maxSpeed = 100;
	}

	public Vehicle(double start, double max, double cons, int speed){
		if(start > max){
			currentFuel = max;
		} else {
			currentFuel = start;
		}
		maxCapacity = max;
		consumption = cons;
		if(speed != 0) {
			maxSpeed = speed;
		} else {
			maxSpeed = 1;
		}
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

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setCurrentFuel(double fuel) {
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
	
	public void setMaxSpeed(int speed) {
		if(speed != 0) {
			maxSpeed = speed;
		} else {
			maxSpeed = 1;
		}
	}

}