package factories;


import java.awt.Point;

import org.apache.commons.collections15.Factory;

import dataStructure.city.infraStructure.CityZone;
import dataStructure.graph.Vertex;


public class GUI_VertexFactory implements Factory<Vertex>  {

	public GUI_VertexFactory() {            
	}

	@Override
	public Vertex create() {
		return new Vertex();
	}
	
	public Vertex create(Point pos){
		return new Vertex(pos);		
	}
	
	public Vertex create(Point pos, CityZone zone){
		return new Vertex(pos,zone);
	}
}
