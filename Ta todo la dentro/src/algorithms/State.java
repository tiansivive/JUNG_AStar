package algorithms;

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
		toVisit = new Vector<>();
		car = null;
	}
	
	public State(Vertex pos, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
		from = null;
		position = pos;
		
		cost = 0;	
		car = veh;
		if(isActive() /*&& pos.isFuelStation()*/) { //TODO: change this
			car.refill();
		}
		
		toVisit = toVis;
		reCalcToVisit();
		order = new Vector<Integer>();
			
		calcHeurWithoutEnd();
		heuristic += toVis.elementAt(order.lastElement()).distance(end);
		calcTotal();
}
	
	public State(State fr, Vertex pos, double cos, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
			from = fr;
			position = pos;
			
			cost = cos;
			car = veh;
			if(isActive() /*&& pos.isFuelStation()*/) { //TODO: change this
				car.refill();
			}
			
			toVisit = toVis;
			reCalcToVisit();
			order = new Vector<Integer>();

			calcHeurWithoutEnd();
			heuristic += toVis.elementAt(order.lastElement()).distance(end);
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
		// tested
		Vertex current = this.position;
		heuristic = 0;
		while(order.size() < toVisit.size()) {
			double minDistance = Double.POSITIVE_INFINITY;
			int index = 0;
			
			for(int i=0; i<toVisit.size(); ++i) {
				if(!order.contains((Integer)i)){
					double testDistance = current.distance(toVisit.elementAt(i));
					if(testDistance < minDistance) {
						minDistance = testDistance;
						index = i;
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
		for(int i = 0; i<toVisit.size(); ++i) {
			if (position.equals(toVisit.elementAt(i))) {
				toVisit.remove(i);
				--i;
			}
		}
		
	}	
	

	public Vector<State> getNeighbor(RoadNetworkGraph<Vertex, Edge> roadNetwork, Vertex end) {
		Vector<State> neighbor = new Vector<State>();
		Vector<Edge> paths = (Vector<Edge>) roadNetwork.getOutEdges(position);
		
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

}