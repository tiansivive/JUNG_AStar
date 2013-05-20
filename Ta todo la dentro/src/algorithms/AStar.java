package algorithms;

import java.util.Vector;

import dataStructure.city.infraStructure.Vehicle;
import dataStructure.graph.Vertex;

public class AStar {

	Vector<State> openlist; //TODO change vector to a priority queue
	Vector<State> closedlist; //TODO change vector to a priority queue
	
	public AStar() {
		openlist = new Vector<State>();
		closedlist = new Vector<State>();
	}
	
	public boolean getPath(Vertex start, Vertex end, Vector<Vertex> toVisit, Vehicle vehicle) {
		openlist = new Vector<State>();
		closedlist = new Vector<State>();
		
		openlist.add(new State(start, toVisit, vehicle, end));
		State current;
		
		while(!openlist.isEmpty()) {
			current = openlist.elementAt(0);
			openlist.remove(0);
			closedlist.add(current);
			
			if(current.isActive()) {
				if(current.isVertex(end)) {
					return true; //TODO: change this, return path or current;
				}
				
				Vector<State> neighbor = current.getNeighbor();
				for (State state_neigh : neighbor) {
					/* THERE IS NO EQUAL STATES, 'cause fuel
					// apply score to state_neigh if not applied at getNeightbor
					boolean found = false;
					for(State state_close : closedlist) {
						if(state_neigh == state_close) {
							found = true;
							if(state_neigh.score < state_close.score) {
								change state_close
							}
						}
					}*/
					openlist.add(state_neigh);
				}
			}
		}
		return false;
		
	}

}
