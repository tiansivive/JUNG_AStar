package logic;

public class Vehicle {
	
	
	int fuelTank;
	int maxCapacity;
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
	}*/
	
	public Vehicle(int start, int max){
		if(start > max){
			fuelTank = max;
		} else {
			fuelTank = start;
		}
		maxCapacity = max;
	}

	public int getFuelTank(){
		return fuelTank;
	}
	
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	public void setFuelTank(int fuel) {
		if(fuel > maxCapacity){
			fuelTank = maxCapacity;
		} else {
			fuelTank = fuel;
		}
	}
	
	public void setMaxCapacity(int cap){
		maxCapacity = cap;
	}
	
	public void refill(){
		fuelTank = maxCapacity;
	}
	
}
