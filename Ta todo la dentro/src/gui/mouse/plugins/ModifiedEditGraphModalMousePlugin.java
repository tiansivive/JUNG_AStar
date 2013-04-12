package gui.mouse.plugins;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.visualization.control.EditingGraphMousePlugin;

public class ModifiedEditGraphModalMousePlugin<V, E> extends EditingGraphMousePlugin<V, E> {

	
	
	public ModifiedEditGraphModalMousePlugin(Factory<V> vertexFactory, Factory<E> edgeFactory) {
		super(vertexFactory, edgeFactory);
		
	}
	
	
	public ModifiedEditGraphModalMousePlugin(int modifiers, Factory<V> vertexFactory, Factory<E> edgeFactory) {
		super(modifiers, vertexFactory, edgeFactory);
		
	}

}
