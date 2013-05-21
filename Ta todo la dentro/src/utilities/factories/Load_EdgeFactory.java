package utilities.factories;

import magicNumbers.Values;

import org.apache.commons.collections15.Factory;

import dataStructure.graph.Edge;

public final class Load_EdgeFactory implements Factory<Edge> {

	public Load_EdgeFactory() {
		
	}

	@Override
	public Edge create() {
		
		Values.EdgesCurrentID = 0;
		return new Edge();
	}

}
