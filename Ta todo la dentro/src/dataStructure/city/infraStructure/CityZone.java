package dataStructure.city.infraStructure;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import magicNumbers.Values;

public class CityZone {

	
	private String name;
	private int id;
	
	private Map<Class<?>,Color> colorRepresentations; 
	
	
	public CityZone(){
		
		Values.CityZonesCurrentID++;
		this.id = Values.CityZonesCurrentID;
		this.name = null;
		this.colorRepresentations = new HashMap<Class<?>, Color>();
		
		
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
	
}
