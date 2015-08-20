package utilities.factories;

import magicNumbers.Values;

import org.apache.commons.collections15.Factory;

import dataStructure.graph.Vertex;

public class Load_VertexFactory implements Factory<Vertex> {

	
	public Load_VertexFactory(){
		
	}
	
	@Override
	public Vertex create() {
		
		Values.VerticesCurrentID = 0;
		return new Vertex();
	}

}
