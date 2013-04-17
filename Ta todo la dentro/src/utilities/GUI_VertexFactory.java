package utilities;


import org.apache.commons.collections15.Factory;

import dataStructure.graph.Vertex;


public class GUI_VertexFactory implements Factory<Vertex>{

	public static boolean default_mode;
	
	public GUI_VertexFactory() {  
		default_mode = true;
	}
	public GUI_VertexFactory(boolean defaultMode) {  
		default_mode = defaultMode;
	}

	@Override
	public Vertex create() {
		return new Vertex();
	}
	
	
}
