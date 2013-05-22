/*package dataStructure.city.infraStructure;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import magicNumbers.Values;

@Deprecated
public class CityZone implements Serializable {


	private static final long serialVersionUID = 6428675141467588918L;
	private String name;
	private int id;

	private Map<Class<?>,Color> colorRepresentations;
	private Set<PointOfInterest> pointsOfInterest;
	
	public CityZone(){

		Values.CityZonesCurrentID++;
		this.id = Values.CityZonesCurrentID;
		this.name = null;
		this.colorRepresentations = new HashMap<Class<?>, Color>();

	}

	public void addPointOfInterest(PointOfInterest poi){
		this.pointsOfInterest.add(poi);
	}
	
	
	public String toString(){

		String toReturn = "Zone ";
		if(name != null){
			toReturn += name;
		}else{
			toReturn += "CZ" + id;
		}	
		return toReturn;	
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Map<Class<?>, Color> getColorRepresentations() {
		return colorRepresentations;
	}
	public void setColorRepresentations(Map<Class<?>, Color> colorRepresentations) {
		this.colorRepresentations = colorRepresentations;
	}
	public Color getObjectsColor(Class<?> key){
		if(colorRepresentations.containsKey(key)){
			return colorRepresentations.get(key);
		}else{
			return null;
		}
	}
	public void setObjectsColor(Class<?> key, Color c){
		colorRepresentations.put(key, c);
	}
	public Set<PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
	}
	public void setPointsOfInterest(Set<PointOfInterest> pointsOfInterest) {
		this.pointsOfInterest = pointsOfInterest;
	}
}*/ //TODO: remove this class
