package logic.graph;


public class Edge{
	
	private Vertex orig;
	private Vertex dest;
	
	private int speedLimit;
	private double distance;
	private int weight;
	private boolean bidirectional;
	
	
	public Edge(Vertex o, Vertex d, int speed, double dist, int w, boolean bidirection){
		
		this.orig = o;
		this.dest = d;
		this.speedLimit = speed;
		this.distance = dist;
		this.weight = w;
		this.bidirectional = bidirection;

	}
	
	
	public Vertex getOrig() {
		return orig;
	}
	public void setOrig(Vertex orig) {
		this.orig = orig;
	}
	public Vertex getDest() {
		return dest;
	}
	public void setDest(Vertex dest) {
		this.dest = dest;
	}
	public int getSpeedLimit() {
		return speedLimit;
	}
	public void setsSpeedLimit(int speed) {
		this.speedLimit = speed;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean getBidirectional() {
		return bidirectional;
	}
	public void setBidirectional(boolean bi) {
		this.bidirectional = bi;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}