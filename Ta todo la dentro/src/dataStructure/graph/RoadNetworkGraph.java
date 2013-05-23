package dataStructure.graph;

import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

public class RoadNetworkGraph<V, E> extends DirectedSparseMultigraph<V, E>{

	private static final long serialVersionUID = 6095920704525216086L;

	private Set<E> selectedEdges;

	private Set<V> pointsToTraverse;
	private V initialVertex;
	private V endVertex;


	public RoadNetworkGraph(){	
		super();
		setSelectedEdges(new HashSet<E>());
		setPointsToTraverse(new HashSet<V>());
		initialVertex = null;
		endVertex = null;
	}


	@SuppressWarnings("unchecked")
	public void updateVertexPositions(AbstractLayout<Vertex, Edge> layout){

		for(Vertex v : (Collection<Vertex>)getVertices()){

			Point position = new Point();
			position.setLocation(layout.getX(v), layout.getY(v));
			v.setPosition(position);	
		}		
	}

	public boolean removeVertex(V vertex) {
		if (!containsVertex(vertex))
			return false;

		// copy to avoid concurrent modification in removeEdge
		Set<E> incident = new HashSet<E>(getIncoming_internal(vertex));
		incident.addAll(getOutgoing_internal(vertex));

		for (E edge : incident)
			removeEdge(edge);

		vertices.remove(vertex);
		pointsToTraverse.remove(vertex);

		return true;
	}

	public boolean isInitialVertex(V v){
		return v.equals(initialVertex);
	}
	public boolean isEndVertex(V v){
		return v.equals(endVertex);
	}
	public boolean isPointToTraverse(V v){
		return pointsToTraverse.contains(v);
	}

	public void addPointToTraverse(V v){
		pointsToTraverse.add(v);
	}
	
	public void removePointToTraverse(V v){
		pointsToTraverse.remove(v);
	}
	public void addSelectedEdge(E edge){	
		if(super.getEdges().contains(edge)){
			selectedEdges.add(edge);
		}
	}
	public void clearSelectedEdges(){	
		selectedEdges.clear();
	}
	public void removeSelectedEdge(E edge){
		selectedEdges.remove(edge);
	}
	public boolean isSelected(E edge){
		return selectedEdges.contains(edge);
	}
	public Set<E> getSelectedEdges() {
		return selectedEdges;
	}
	public void setSelectedEdges(Set<E> selectedEdges) {
		this.selectedEdges = selectedEdges;
	}
	public Set<V> getPointsToTraverse() {
		return pointsToTraverse;
	}
	public void setPointsToTraverse(Set<V> pointsToPass) {
		this.pointsToTraverse = pointsToPass;
	}
	public V getInitialVertex() {
		return initialVertex;
	}
	public void setInitialVertex(V initialVertex) {
		this.initialVertex = initialVertex;
	}
	public V getEndVertex() {
		return endVertex;
	}
	public void setEndVertex(V endVertex) {
		this.endVertex = endVertex;
	}

}
