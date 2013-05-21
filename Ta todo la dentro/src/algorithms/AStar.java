package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

import dataStructure.city.infraStructure.Vehicle;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;

public class AStar {
	
	//TODO: test and think if this is a abstract class

	private static Comparator<State> comparator = new Comparator<State>() {
		public int compare(State s1, State s2) {
			return (int)(s1.getTotal() - s2.getTotal());
		}
	};

	PriorityQueue<State> openlist;
	PriorityQueue<State> closedlist;

	public AStar() {
		openlist = new PriorityQueue<State>(1,comparator);
		closedlist = new PriorityQueue<State>(1,comparator);
	}

	public boolean getPath(Vertex start, Vertex end, Vector<Vertex> toVisit, Vehicle vehicle, RoadNetworkGraph<Vertex, Edge> roadNetwork) {
		openlist = new PriorityQueue<State>(1,comparator);
		closedlist = new PriorityQueue<State>(1,comparator);

		openlist.add(new State(start, toVisit, vehicle, end));
		State current;

		while(!openlist.isEmpty()) {
			current = openlist.poll();
			closedlist.add(current);

			if(current.isActive()) {
				if(current.isVertex(end)) {
					return true; //TODO: change this, return path or current;
				}

				Vector<State> neighbor = current.getNeighbor(roadNetwork, end);
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
