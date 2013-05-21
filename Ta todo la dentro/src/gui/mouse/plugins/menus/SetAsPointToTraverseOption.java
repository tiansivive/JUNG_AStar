package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import magicNumbers.Values;
import dataStructure.graph.RoadNetworkGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;

public class SetAsPointToTraverseOption<V> extends JMenuItem implements VertexMenuListener<V>{

	private static final long serialVersionUID = -2003267797469062963L;

	private V vertex;
	private VisualizationViewer<V, ?> vv;

	public SetAsPointToTraverseOption(){
		super(Values.vertex_set_as_point_to_traverse);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				Graph<V,?> g = vv.getGraphLayout().getGraph();
				if(g instanceof RoadNetworkGraph){
					((RoadNetworkGraph<V,?>) g).addPointToTraverse(vertex);
				}
				vv.repaint();
			}
		});
	}

	@Override
	public void setVertexAndView(V v, VisualizationViewer<V, ?> visComp) {
		this.vertex = v;
		this.vv = visComp;
	}
	
}
