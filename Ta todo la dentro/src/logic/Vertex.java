package logic;
import java.awt.Point;
import java.util.*;


public class Vertex{
	
	private int id;
	
	private ArrayList<Edge> adj;
	private boolean visited;
	private boolean processing;
	private int indegree;
	
	private Point pos;
	
	
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
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}