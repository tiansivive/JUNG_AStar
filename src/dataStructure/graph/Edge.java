package dataStructure.graph;

import java.io.Serializable;

import magicNumbers.Values;


public class Edge implements Serializable{
	
	private static final long serialVersionUID = 1678390781343595348L;

	private int id;
	
	private int speedLimit;
	private double distance;
	private double capacity;
	
	private String name;
	
	private boolean animated;
	
	public Edge(){
		
		Values.EdgesCurrentID++;
		this.id = Values.EdgesCurrentID;
		this.speedLimit = Values.default_edge_speedLimit;
		this.distance = Values.default_edge_distance;
		this.capacity = Values.default_edge_capacity;
		animated = false;
		
	}
	
	public Edge(int speed, double dist){

		Values.EdgesCurrentID++;
		this.id = Values.EdgesCurrentID;
		this.speedLimit = speed;
		this.distance = dist;
		this.capacity = -1;
		animated = false;
	}

	public Edge(int speed, double dist, double c){
		
		Values.EdgesCurrentID++;
		this.id = Values.EdgesCurrentID;
		this.speedLimit = speed;
		this.distance = dist;
		this.capacity = c;
		animated = false;

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

	public int getId() {	
		return id;
	}

	public void setId(int ID) {
		
		this.id = ID;
	}

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
}