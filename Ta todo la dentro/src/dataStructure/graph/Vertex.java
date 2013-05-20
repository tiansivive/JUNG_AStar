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
	
	private CityZone zone;
	private PointOfInterest pointOfInterest;
	
	private VertexType type;

	public Vertex(){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.zone = null;
		this.pointOfInterest = null;
	}

	
	public Vertex(CityZone z){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.zone = z;
		this.pointOfInterest = null;
	}	
	
	public Vertex(String vName, CityZone cZone, VertexType vType){
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = vName;
		this.zone = cZone;
		this.type = vType;
		this.pointOfInterest = null;
	}
	
	public Vertex(String vName, CityZone cZone, VertexType vType, PointOfInterest poi){
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = vName;
		this.zone = cZone;
		this.type = vType;
		if(vType != VertexType.BUILDING_CONNECTION){
			System.err.println("ERROR: cannot create Vertex with non-null PointOfInterest if VertexType not BUILDING_CONNECTION");
			throw new IllegalArgumentException();
		}else{
			this.pointOfInterest = poi;
			cZone.addPointOfInterest(poi);
		}
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

	public double distance(Vertex vertex) {
		return this.getPosition().distance(vertex.getPosition());
	}
	
	public Point getPosition() { //TODO: VILA-VERDE faz isto mazé!
		if(pointOfInterest != null) {
			return pointOfInterest.getPos();
		}
		return new Point(0,0);
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