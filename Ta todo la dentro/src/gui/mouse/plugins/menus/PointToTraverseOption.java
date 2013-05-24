package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import magicNumbers.Values;
import dataStructure.graph.RoadNetworkGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;
import gui.mouse.plugins.menus.dialogs.VertexPriorityDialog;

public class PointToTraverseOption<V> extends JMenuItem implements VertexMenuListener<V>{

	private static final long serialVersionUID = -2003267797469062963L;

	private V vertex;
	private VisualizationViewer<V, ?> vv;

	public PointToTraverseOption(){
		
		super();		
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				
				VertexPriorityDialog<V> priority = new VertexPriorityDialog<V>(vertex);
				priority.setVisible(true);
		
				
				Graph<V,?> g = vv.getGraphLayout().getGraph();
				if(isVertexAlreadyPointToTraverse()){
					((RoadNetworkGraph<V, ?>) g).removePointToTraverse(vertex);		
				}else{
					((RoadNetworkGraph<V, ?>) g).addPointToTraverse(vertex);
				}
				vv.repaint();
			}
		});
	}

	@Override
	public void setVertexAndView(V v, VisualizationViewer<V, ?> visComp) {
		this.vertex = v;
		this.vv = visComp;
		
		if(isVertexAlreadyPointToTraverse()){
			this.setText(Values.vertex_remove_as_point_to_traverse);
		}else{
			this.setText(Values.vertex_set_as_point_to_traverse);
		}

	}
	
	private boolean isVertexAlreadyPointToTraverse(){
		
		Graph<V,?> g = vv.getGraphLayout().getGraph();
		if(g instanceof RoadNetworkGraph){
			return ((RoadNetworkGraph<V,?>) g).isPointToTraverse(vertex);
		}
		return false;
	}
	
}
