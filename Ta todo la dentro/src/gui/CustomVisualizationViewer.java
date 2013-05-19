package gui;


import java.util.Set;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class CustomVisualizationViewer<V, E> extends VisualizationViewer<V, E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8191211546759881598L;
	
	
	private Set<Layout<V,E>> layouts;
	

	public CustomVisualizationViewer(Layout<V, E> layout) {
		super(layout);
		
	}
	
	
	public Graph<V,E> getGraphFromCurrentLayout(){
			
       return getModel().getGraphLayout().getGraph();
	}
	
	
	public void addLayout(Layout<V,E> layout){
		
		this.layouts.add(layout);
	}
	
	public void removeLayout(Layout<V,E> layout){
		
		this.layouts.remove(layout);
	}
	
	public void setActiveLayout(Layout<V,E> layout){
		
		if(layouts.contains(layout)){
			
			//TODO
		}
	}
	

}
