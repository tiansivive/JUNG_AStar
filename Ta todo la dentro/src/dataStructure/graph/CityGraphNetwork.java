package dataStructure.graph;



public class CityGraphNetwork{
	
	private RoadNetworkGraph<Vertex, Edge> roadNetwork;

	public CityGraphNetwork(){
		roadNetwork = new RoadNetworkGraph<Vertex, Edge>();
	}
	
	public CityGraphNetwork(RoadNetworkGraph<Vertex, Edge> network){
		roadNetwork = network;
	}
	
	public RoadNetworkGraph<Vertex, Edge> getRoadNetwork() {
		return roadNetwork;
	}
	public void setRoadNetwork(RoadNetworkGraph<Vertex, Edge> roadNetwork) {
		this.roadNetwork = roadNetwork;
	}
	
}