package algorithms;

import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import dataStructure.city.infraStructure.Vehicle;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;

public class State {

	State from;
	Vertex position;
	
	double cost;
	double heuristic;
	double total;
	
	Vector<Vertex> toVisit;
	Vector<Integer> order;
	Vehicle car;
	
	//Constructors
	public State() {
		from = null;
		position = null;
		cost = 0;
		heuristic = 0;
		total = 0;
		toVisit = new Vector<Vertex>();
		car = null;
	}
	
	public State(Vertex pos, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
		from = null;
		position = pos;
		
		cost = 0;
		heuristic = 0;
		total = 0;
		car = veh;
		if(isActive() && pos.isFuelStation()) {
			car.refill();
		}
		
		toVisit = new Vector<>();
		for(Vertex vertex : toVis) {
			toVisit.add(vertex);
		}
		reCalcToVisit();
		order = new Vector<Integer>();
			
		calcHeurWithoutEnd();
		if(order.size() > 0) {
			heuristic += toVis.elementAt(order.lastElement()).distance(end);
		} else {
			heuristic += position.distance(end);
		}
		calcTotal();
}
	
	public State(State fr, Vertex pos, double cos, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
			from = fr;
			position = pos;
			
			cost = 0;
			heuristic = 0;
			total = 0;
			car = veh;
			if(isActive() && pos.isFuelStation()) {
				car.refill();
			}
			
			toVisit = new Vector<>();
			for(Vertex vertex : toVis) {
				toVisit.add(vertex);
			}
			reCalcToVisit();
			order = new Vector<Integer>();

			calcHeurWithoutEnd();
			if(order.size() > 0) {
				heuristic += toVis.elementAt(order.lastElement()).distance(end);
			} else {
				heuristic += position.distance(end);
			}
			calcTotal();
	}

	//Getters
	public State getFrom() {
		return from;
	}

	public Vertex getPosition() {
		return position;
	}

	public double getCost() {
		return cost;
	}

	public double getHeuristic() {
		return heuristic;
	}
	
	public double getTotal() {
		return total;
	}

	public Vector<Vertex> getToVisit() {
		return toVisit;
	}

	public Vehicle getCar() {
		return car;
	}
	
	
	
	private void calcHeurWithoutEnd() {
		Vertex current = this.position;
		heuristic = 0;
		
		while(order.size() < toVisit.size()) {
			int index = 0;		
			double minDistance = Double.POSITIVE_INFINITY;
			int minOrder = Integer.MAX_VALUE;
			
			for(int i=0; i<toVisit.size(); ++i) {
				if(!order.contains((Integer)i)){
					Vertex vertex = toVisit.elementAt(i);
				if(vertex.getOrder() != 0) {
						if(vertex.getOrder() < minOrder) {
							minDistance = current.distance(vertex);
							minOrder = vertex.getOrder();
							index = i;
						} else {
							if(vertex.getOrder() == minOrder) {
								double testDistance = current.distance(vertex);
								if(testDistance < minDistance) {
									minDistance = testDistance;
									index = i;
								}
							}
						}
					} else {
						double testDistance = current.distance(vertex);
						if(testDistance < minDistance) {
							minDistance = testDistance;
							index = i;
						}
					}
				}
			}
			
			order.add(index);
			heuristic += minDistance;
			current = toVisit.elementAt(index);
		}
		
	}
	
	private void calcTotal() {
		total = cost + heuristic;
	}
	
	private void reCalcToVisit() {
		int minOrder = Integer.MAX_VALUE;
		for (Vertex vertex : toVisit) {
			if(vertex.getOrder() > 0 && vertex.getOrder() < minOrder) {
				minOrder = vertex.getOrder();
			}
		}
		for(int i = 0; i<toVisit.size(); ++i) {
			if (position.equals(toVisit.elementAt(i)) && toVisit.elementAt(i).getOrder() <= minOrder) {
				toVisit.remove(i);
				--i;
			}
		}
		
	}	
	

	public Vector<State> getNeighbor(RoadNetworkGraph<Vertex, Edge> roadNetwork, Vertex end) {
		Vector<State> neighbor = new Vector<State>();
		Collection<Edge> paths = roadNetwork.getOutEdges(position);
		
		for(Edge edge : paths) {
			Vertex newVertex = roadNetwork.getDest(edge);
			
			double distance = edge.getDistance();
			double newCost = this.cost + distance;
			Vehicle newCar = new Vehicle(car);
			newCar.travel(distance);
			if(newCar.getCurrentFuel() >= 0) {
				neighbor.add(new State(this,newVertex,newCost,toVisit,newCar, end));
			}
		}
		return neighbor;
	}

	public boolean isVertex(Vertex vertex) {
		return position.equals(vertex);
	}

	public boolean isActive() {
	
		if(position == null) {
			return false;
		}
		if(car.getCurrentFuel() < 0) {
			return false;
		}
		
		return true;
	}
	
	public boolean isWorst(State state) {
		if(position == state.position) {
			if(car.getCurrentFuel() <= state.car.getCurrentFuel()) {
				if(state.toVisit.containsAll(toVisit) && state.toVisit.size() == toVisit.size()) {
					return true;	
				}
			}
		}
		return false;
	}

}
