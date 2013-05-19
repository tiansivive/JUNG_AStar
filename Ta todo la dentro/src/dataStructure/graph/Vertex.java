package dataStructure.graph;

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
	
	private boolean visited;
	private boolean processing;
	
	private CityZone zone;
	private PointOfInterest pointOfInterest;
	
	private VertexType type;

	public Vertex(){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.zone = null;
		this.pointOfInterest = null;
	}

	
	public Vertex(CityZone z){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.zone = z;
		this.pointOfInterest = null;
	}	
	
	public Vertex(String vName, CityZone cZone, VertexType vType){
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = vName;
		this.visited = false;
		this.processing = false;
		this.zone = cZone;
		this.type = vType;
		this.pointOfInterest = null;
	}
	
	public Vertex(String vName, CityZone cZone, VertexType vType, PointOfInterest poi){
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = vName;
		this.visited = false;
		this.processing = false;
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
}