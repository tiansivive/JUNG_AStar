package factories;


import org.apache.commons.collections15.Factory;

import dataStructure.graph.Vertex;


public class GUI_VertexFactory implements Factory<Vertex>{

	public GUI_VertexFactory() {            
	}

	@Override
	public Vertex create() {
		return new Vertex();
	}
	
	
}
