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
	
	double distance;
	double time;
	double heuristic;
	
	Vector<Vertex> toVisit;
	Vector<Integer> order;
	Vehicle car;
	
	//Constructors
	public State() {
		from = null;
		position = null;
		distance = 0;
		time = 0;
		heuristic = 0;
		toVisit = new Vector<Vertex>();
		car = null;
	}
	
	public State(Vertex pos, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
		from = null;
		position = pos;
		
		distance = 0;
		time = 0;
		heuristic = 0;
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
}
	
	public State(State fr, Vertex pos, double dist, double tim, Vector<Vertex> toVis, Vehicle veh, Vertex end) {
			from = fr;
			position = pos;
			
			distance = dist;
			time = tim;
			heuristic = 0;
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
	}

	//Getters
	public State getFrom() {
		return from;
	}

	public Vertex getPosition() {
		return position;
	}

	public double getDistance() {
		return distance;
	}
	
	public double getTime() {
		return time;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public Vector<Vertex> getToVisit() {
		return toVisit;
	}

	public Vehicle getCar() {
		return car;
	}
	public double getCost(int type) {
		double ret = 0;
		if(type == AStar.DISTANCE) {
			ret += distance;
			ret += heuristic;
		} else {
			if(type == AStar.TIME) {
				ret += time;
				ret += heuristic/(double)(car.getMaxSpeed());
			}
		}
		return ret;
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
			
			double dist = edge.getDistance();
			int maxSpd = Math.min(edge.getSpeedLimit(), car.getMaxSpeed());
			double tim = dist/(double)(maxSpd);
			double newDist = this.distance + dist;
			double newTime = this.time + tim;
			Vehicle newCar = new Vehicle(car);
			newCar.travel(dist);
			if(newCar.getCurrentFuel() >= 0) {
				neighbor.add(new State(this,newVertex,newDist,newTime,toVisit,newCar, end));
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
	
	public boolean isWorst(State state, int type) {
		if(position == state.position) {
			if(car.getCurrentFuel() <= state.car.getCurrentFuel()) {
				if((type == AStar.DISTANCE  && distance >= state.distance) || (type == AStar.TIME  && time >= state.time)) {
					if(state.toVisit.containsAll(toVisit) && state.toVisit.size() == toVisit.size()) {
						return true;	
					}
				}
			}
		}
		return false;
	}

}
