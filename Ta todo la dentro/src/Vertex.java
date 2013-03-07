import java.util.*;


public class Vertex{
	
	private ArrayList<Edge> adj;
	private boolean visited;
	private boolean processing;
	private int indegree;
	
	
	
	public ArrayList<Edge> getAdj() {
		return adj;
	}
	public void setAdj(ArrayList<Edge> adj) {
		this.adj = adj;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isProcessing() {
		return processing;
	}
	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	public int getIndegree() {
		return indegree;
	}
	public void setIndegree(int indegree) {
		this.indegree = indegree;
	}
	
	
	
}