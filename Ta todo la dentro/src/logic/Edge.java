package logic;
public class Edge{
	
	private Vertex orig;
	private Vertex dest;
	
	private double cost;
	private double distance;
	private boolean bidirectional;
	
	
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	
}