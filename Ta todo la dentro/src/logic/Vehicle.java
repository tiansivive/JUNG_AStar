package logic;

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
		consumption = 1;
	}
	
	public Vehicle(int start, int max, int cons){
		if(start > max){
			currentFuel = max;
		} else {
			currentFuel = start;
		}
		maxCapacity = max;
		consumption = cons;
	}

	public int getCurrentFuel(){
		return currentFuel;
	}
	
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	public int getConsumption() {
		return consumption;
	}
	
	public void setCurrentFuel(int fuel) {
		if(fuel > maxCapacity){
			currentFuel = maxCapacity;
		} else {
			currentFuel = fuel;
		}
	}
	
	public void travel(int distance) {
		currentFuel -= distance*consumption;
	}
	
	public void refill(){
		currentFuel = maxCapacity;
	}
	
	public void setMaxCapacity(int cap){
		maxCapacity = cap;
	}

	public void setConsumption(int cons) {
		consumption = cons;
	}
	
}
