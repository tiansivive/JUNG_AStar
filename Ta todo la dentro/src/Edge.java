

public class Edge{
	
	private Vertex orig;
	private Vertex dest;
	
	private double weight;
	private float flow;
	
	
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public float getFlow() {
		return flow;
	}
	public void setFlow(float flow) {
		this.flow = flow;
	}
	
}