package gui;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Map;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;

public class CustomLayout<V, E> extends AbstractLayout<V, E>{


	public CustomLayout(Graph<V, E> graph) {
		super(graph);	
	}
	protected CustomLayout(Graph<V, E> graph, Dimension size) {
		super(graph, size);
	}
	protected CustomLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer) {
		super(graph, initializer);	
	}
	protected CustomLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer, Dimension size) {
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

	public Map<V,Point2D> getLocation(){
		return this.locations;
	}


}
