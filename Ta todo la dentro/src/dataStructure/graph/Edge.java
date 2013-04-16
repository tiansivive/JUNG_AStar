package dataStructure.graph;

import magicNumbers.Values;


public class Edge{
	
	private int id;
	
	private int speedLimit;
	private double distance;
	private double weight;
	private double capacity; //TODO not for now
	private boolean bidirectional;
	
	private String name;
	
	
	public Edge(int speed, double dist, double w, boolean bidirection){

		Values.EdgesCurrentID++;
		this.id = Values.EdgesCurrentID;
		this.speedLimit = speed;
		this.distance = dist;
		this.weight = w;
		this.bidirectional = bidirection;
		this.capacity = -1;
	}

	public Edge(int speed, double dist, double w, double c, boolean bidirection){
		
		Values.EdgesCurrentID++;
		this.id = Values.EdgesCurrentID;
		this.speedLimit = speed;
		this.distance = dist;
		this.weight = w;
		this.capacity = c;
		this.bidirectional = bidirection;
	}
	
	public String toString(){
	
		String toReturn = "";
		if(name != null){
			toReturn += name;
		}else{
			toReturn += "E" +id;
		}
		return toReturn;
	}

	
	public int getSpeedLimit() {
		return speedLimit;
	}
	public void setSpeedLimit(int speed) {
		this.speedLimit = speed;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean isBidirectional() {
		return bidirectional;
	}
	public void setBidirectional(boolean bi) {
		this.bidirectional = bi;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public String getName() {
		if(name == null){
			return "E" +id;
		}else{
			return name;
		}
	}
	public void setName(String name) {
		this.name = name;
	}
}