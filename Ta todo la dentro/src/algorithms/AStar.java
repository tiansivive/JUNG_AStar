package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;

import dataStructure.city.infraStructure.Vehicle;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;

public class AStar {

	public static final int DISTANCE = 0;
	public static final int TIME = 1;

	private static Comparator<State> comparatorDistance = new Comparator<State>() {
		public int compare(State s1, State s2) {
			return (int)(s1.getCost(DISTANCE) - s2.getCost(DISTANCE));
		}
	};
	
	private static Comparator<State> comparatorTime = new Comparator<State>() {
		public int compare(State s1, State s2) {
			return (int)(s1.getCost(TIME) - s2.getCost(TIME));
		}
	};

	private static PriorityQueue<State> openlist;
	private static PriorityQueue<State> closedlist;

	public static int openSize() {
		return openlist.size();
	}

	public static int closedSize() {
		return closedlist.size();
	}

	public static State getPath(Vertex start, Vertex end, Vector<Vertex> toVisit, Vehicle vehicle, RoadNetworkGraph<Vertex, Edge> roadNetwork, int type) {
		openlist = new PriorityQueue<State>(1,comparatorDistance);
		closedlist = new PriorityQueue<State>(1,comparatorDistance);
		
		if(type == TIME) {
			openlist = new PriorityQueue<State>(1,comparatorTime);
			closedlist = new PriorityQueue<State>(1,comparatorTime);
		}

		openlist.add(new State(start, toVisit, vehicle, end));
		State current;

		while(!openlist.isEmpty()) {
			current = openlist.poll();
			closedlist.add(current);

			if(current.isActive()) {
				if(current.isVertex(end) && current.getToVisit().size() == 0) {
					return current;
				}

				Vector<State> neighbor = current.getNeighbor(roadNetwork, end);
				for (State state_neigh : neighbor) {
					boolean worst = false;
					
					for(State state_close : closedlist) {
						if(state_neigh.isWorst(state_close, type)) {
							worst = true;
							continue;
						}
					}
					for(State state_close : openlist) {
						if(state_neigh.isWorst(state_close, type)) {
							worst = true;
							continue;
						}
					}

					if(!worst) {
						openlist.add(state_neigh);
					}
				}
			}
		}
		return null;
	}

	public static Vector<State> getPathState(State last) {
		Vector<State> path = new Vector<>();
		if(last != null) {
			State current = last;
			while(current.getFrom() != null) {
				path.add(current);
				current = current.getFrom();
			}
			path.add(current);
		}
		return path;
	}

	public static Vector<Vertex> getPathVertex(State last) {
		Vector<Vertex> path = new Vector<>();
		if(last != null) {
			State current = last;
			while(current.getFrom() != null) {
				path.add(current.getPosition());
				current = current.getFrom();
			}
			path.add(current.getPosition());
		}
		return path;
	}
}
