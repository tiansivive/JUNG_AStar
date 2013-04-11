package factories;

import logic.graph.Edge;
import magicNumbers.Values;

import org.apache.commons.collections15.Factory;

public class GUI_EdgeFactory implements Factory<Edge>{

	public GUI_EdgeFactory(){
	}
	
	@Override
	public Edge create() {
		
		Edge e = new Edge(Values.default_edge_speedLimit,
								Values.default_edge_distance,
								Values.default_edge_weight,
								Values.default_edge_capacity,
								Values.default_edge_bidirectionality);
		return e;
	}

	
}
