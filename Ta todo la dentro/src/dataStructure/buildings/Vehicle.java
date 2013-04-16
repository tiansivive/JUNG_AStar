package dataStructure.buildings;

public class Vehicle {
	
	
	private int currentFuel;
	private int maxCapacity;
	private int consumption;

	
	public Vehicle(int start, int max){
		if(start > max){
			currentFuel = max;
		} else {
			currentFuel = start;
		}
		maxCapacity = max;
	}

	public int getCurrentFuel(){
		return currentFuel;
	}
	
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	public void setCurrentFuel(int fuel) {
		if(fuel > maxCapacity){
			currentFuel = maxCapacity;
		} else {
			currentFuel = fuel;
		}
	}
	
	public void setMaxCapacity(int cap){
		maxCapacity = cap;
	}
	
	public void refill(){
		currentFuel = maxCapacity;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	
}
