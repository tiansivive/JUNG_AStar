package factories;

import logic.graph.Vertex;

import org.apache.commons.collections15.Factory;


public class GUI_VertexFactory implements Factory<Vertex>  {

	public GUI_VertexFactory() {            
	}

	@Override
	public Vertex create() {
		Vertex v = new Vertex();
		return v;
	}
}
