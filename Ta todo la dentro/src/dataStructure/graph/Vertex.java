package dataStructure.graph;

import java.awt.Point;
import java.io.Serializable;

import magicNumbers.Values;
import dataStructure.city.infraStructure.CityZone;
import dataStructure.city.infraStructure.PointOfInterest;

public class Vertex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5790657416363651850L;
	private int id;
	private String name;
	
	private Point pos;
	
	private CityZone zone;
	private PointOfInterest pointOfInterest;
	
	private VertexType type;

	public Vertex(){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = "V" + id;
		type = VertexType.INTERSECTION;
	}
	
	public Vertex(String n){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = n;
		type = VertexType.INTERSECTION;
	}
	
	public Vertex(VertexType t){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = "V" + id;
		type = t;
	}
	
	public Vertex(String n, VertexType t){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = n;
		type = t;
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
	public VertexType getType() {
		return type;
	}
	public void setType(VertexType type) {
		this.type = type;
	}
	public PointOfInterest getPointOfInterest() {
		return pointOfInterest;
	}
	public void setPointOfInterest(PointOfInterest pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}
	public Point getPosition() { 
		return pos;
	}
	public void setPosition(Point p) {
		this.pos = p;
	}
	
	
	public double distance(Vertex vertex) {
		return this.getPosition().distance(vertex.getPosition());
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}