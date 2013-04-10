package logic.graph;


import java.util.*;

public class Graph{
	
	
	private Map<Integer, Vertex> vertices;
	private Map<Integer, Edge> edges;
	
		
	
	public Map<Integer, Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(Map<Integer, Vertex> vertices) {
		this.vertices = vertices;
	}
	public Map<Integer, Edge> getEdges() {
		return edges;
	}
	public void setEdges(Map<Integer, Edge> edges) {
		this.edges = edges;
	}
	

}