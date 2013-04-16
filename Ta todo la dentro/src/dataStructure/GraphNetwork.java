package dataStructure;


import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class GraphNetwork{
	
	private DirectedSparseMultigraph<Vertex, Edge> roadNetwork;

	public GraphNetwork(){
		roadNetwork = new DirectedSparseMultigraph<Vertex, Edge>();
	}
	
	public GraphNetwork(DirectedSparseMultigraph<Vertex, Edge> network){
		roadNetwork = network;
	}
	
	public void addEdgeToRoadNetwork(Edge e, Vertex src, Vertex dest){
		
		EdgeType type;	
		if(e.isBidirectional()){
			type = EdgeType.UNDIRECTED;
		}else{
			type = EdgeType.DIRECTED;
		}		
		roadNetwork.addEdge(e, src, dest, type);
	}
	
	public void addVertexToRoadNetwork(Vertex v){	
		roadNetwork.addVertex(v);		
	}
	
	public DirectedSparseMultigraph<Vertex, Edge> getRoadNetwork() {
		return roadNetwork;
	}
	public void setRoadNetwork(DirectedSparseMultigraph<Vertex, Edge> roadNetwork) {
		this.roadNetwork = roadNetwork;
	}
	
}