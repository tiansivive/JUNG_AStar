package gui.mouse;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;

public class InteractiveModalGraphMouse<V, E> extends EditingModalGraphMouse<V, E> {

	
	
	public InteractiveModalGraphMouse(RenderContext<V, E> rc, Factory<V> vertexFactory, Factory<E> edgeFactory) {
		super(rc, vertexFactory, edgeFactory);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
