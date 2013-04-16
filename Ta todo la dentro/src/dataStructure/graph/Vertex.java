package dataStructure.graph;

import java.awt.Point;

import magicNumbers.Values;
import dataStructure.city.infraStructure.CityZone;

public class Vertex{
	
	private int id;
	private String name;
	
	private boolean visited;
	private boolean processing;
	
	private Point pos;	
	private CityZone zone;
	
	
	public Vertex(){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.pos = null;
		this.zone = null;
	}
	
	public Vertex(Point p){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.zone = null;
		this.pos = p;
	}	

	
	public Vertex(Point p, CityZone z){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.pos = p;
		this.zone = z;
	}	

	
	@Override
	public String toString(){
		
		String toReturn = "";
		if(name != null){
			toReturn += name;
		}else{
			toReturn += "V" +id;
		}
		return toReturn;
	}
	
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isProcessing() {
		return processing;
	}
	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityZone getZone() {
		return zone;
	}
	public void setZone(CityZone zone) {
		this.zone = zone;
	}	
}