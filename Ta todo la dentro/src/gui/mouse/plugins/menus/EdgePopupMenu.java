package gui.mouse.plugins.menus;


import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import dataStructure.graph.Edge;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.EdgeMenuListener;

import magicNumbers.Values;


public class EdgePopupMenu extends JPopupMenu {        
	
	private static final long serialVersionUID = -9054754601674300108L;

	public EdgePopupMenu(final JFrame frame) {
		super(Values.edge_popupMenu_name);
		this.add(new DeleteEdgeMenuOption<Edge>());
		this.addSeparator();
		this.add(new CapacityDisplay());
		this.add(new SpeedLimitDisplay());
		this.add(new DistanceDisplay());
		this.addSeparator();
		this.add(new ChangeEdgePropertiesMenuOption(frame)); 
	}

	
	private static class CapacityDisplay extends JMenuItem implements EdgeMenuListener<Edge> {

		private static final long serialVersionUID = -1423088391223512823L;
		public void setEdgeAndView(Edge e, VisualizationViewer<?, Edge>  visComp) {
			this.setText("Capacity of " + e + ": " + e.getCapacity());
		}
	}
	private static class SpeedLimitDisplay extends JMenuItem implements EdgeMenuListener<Edge> {

		private static final long serialVersionUID = 8937828949660652216L;
		public void setEdgeAndView(Edge e, VisualizationViewer<?, Edge>  visComp) {
			this.setText("Speed Limit of " + e + ": " + e.getSpeedLimit());
		}
	}
	private static class DistanceDisplay extends JMenuItem implements EdgeMenuListener<Edge> {
	
		private static final long serialVersionUID = 2795508101247118760L;
		public void setEdgeAndView(Edge e, VisualizationViewer<?, Edge>  visComp) {
			this.setText("Distance of " + e + ": " + e.getDistance());
		}
	}

	
	
}