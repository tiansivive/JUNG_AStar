package gui;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Edge;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;

public class CustomLayout<V, E> extends AbstractLayout<Vertex, Edge>{

	
	public CustomLayout(Graph<Vertex, Edge> graph) {
		super(graph);
	}
	protected CustomLayout(Graph<Vertex, Edge> graph, Dimension size) {
		super(graph, size);
	}
	protected CustomLayout(Graph<Vertex, Edge> graph, Transformer<Vertex, Point2D> initializer) {
		super(graph, initializer);	
	}
	protected CustomLayout(Graph<Vertex, Edge> graph, Transformer<Vertex, Point2D> initializer, Dimension size) {
		super(graph, initializer, size);
	}

	@Override
	public void initialize() {
		System.out.println("CALLED INITIALIZE ON CUSTOM_LAYOUT");
	}
	@Override
	public void reset() {
		System.out.println("CALLED RESET ON CUSTOM_LAYOUT");
	}

	

}
