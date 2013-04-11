package gui.mouse;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;

public class InteractiveModalGraphMouse<V, E> extends DefaultModalGraphMouse<V, E> {

	
	private GraphMousePlugin editingPlugin;
	
	public InteractiveModalGraphMouse(){
		
		
		
	}
	
	public InteractiveModalGraphMouse(Factory<V> v_factory, Factory<E> e_factory){
		
	}

	public GraphMousePlugin getEditingPlugin() {
		return editingPlugin;
	}

	public void setEditingPlugin(GraphMousePlugin editingPlugin) {
		this.editingPlugin = editingPlugin;
	}
	
	
}
