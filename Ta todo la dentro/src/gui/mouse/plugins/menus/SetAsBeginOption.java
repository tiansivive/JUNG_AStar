package gui.mouse.plugins.menus;

import dataStructure.graph.RoadNetworkGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import magicNumbers.Values;

public class SetAsBeginOption<V> extends JMenuItem implements VertexMenuListener<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3734921165888918221L;
	
	private V vertex;
	private VisualizationViewer<V, ?> vv;

	public SetAsBeginOption(){
		super(Values.vertex_set_as_begin);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				Graph<V,?> g = vv.getGraphLayout().getGraph();
				if(g instanceof RoadNetworkGraph){
					((RoadNetworkGraph<V,?>) g).setInitialVertex(vertex);
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
