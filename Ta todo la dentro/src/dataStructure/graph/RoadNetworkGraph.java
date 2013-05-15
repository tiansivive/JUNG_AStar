package dataStructure.graph;

import java.util.HashSet;
import java.util.Set;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

public class RoadNetworkGraph<V, E> extends DirectedSparseMultigraph<V, E>{

	private static final long serialVersionUID = 6095920704525216086L;
	
	private Set<E> selectedEdges;
	
	
	public RoadNetworkGraph(){	
		super();
		setSelectedEdges(new HashSet<E>());
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
	

}
