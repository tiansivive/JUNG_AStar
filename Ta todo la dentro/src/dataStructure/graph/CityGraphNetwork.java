package dataStructure.graph;


import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class CityGraphNetwork{
	
	private DirectedSparseMultigraph<Vertex, Edge> roadNetwork;

	public CityGraphNetwork(){
		roadNetwork = new DirectedSparseMultigraph<Vertex, Edge>();
	}
	
	public CityGraphNetwork(DirectedSparseMultigraph<Vertex, Edge> network){
		roadNetwork = network;
	}
	
	public DirectedSparseMultigraph<Vertex, Edge> getRoadNetwork() {
		return roadNetwork;
	}
	public void setRoadNetwork(DirectedSparseMultigraph<Vertex, Edge> roadNetwork) {
		this.roadNetwork = roadNetwork;
	}
	
}