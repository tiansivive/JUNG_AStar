package dataStructure.city.infraStructure;

import java.util.Set;


@Deprecated
public class City {

	
	private String name;
	
	private Set<CityZone> cityZones;
	
	public City(){
		this.setName(null);
	}
	
	public City(String name){
		this.setName(name);
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<CityZone> getCityZones() {
		return cityZones;
	}
	public void setCityZones(Set<CityZone> cityZones) {
		this.cityZones = cityZones;
	}
	
}
