package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import dataStructure.graph.Edge;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.EdgeMenuListener;
import gui.mouse.plugins.menus.auxiliarInterfaces.MenuPointListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import magicNumbers.Values;

public class ChangeEdgePropertiesMenuOption extends JMenuItem implements EdgeMenuListener<Edge>, MenuPointListener {

	private static final long serialVersionUID = 4681389660034440513L;

	private Edge edge;
	private VisualizationViewer<?, Edge> visComp;
	private Point2D pos;
	
	@Override
	public void setEdgeAndView(Edge e, VisualizationViewer<?, Edge> visView) {
		this.edge = e;
		this.setVisComp(visView);
	}

	@Override
	public void setPoint(Point2D point) {
		this.pos = point;
	}

	public ChangeEdgePropertiesMenuOption(final JFrame frame) {            
		super(Values.edge_properties_editor_menu_entry);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EdgePropertyDialog dialog = new EdgePropertyDialog(edge);
				dialog.setLocation((int)pos.getX() + frame.getX(), (int)pos.getY() + frame.getY());
				dialog.setVisible(true);
			}
		});
	}
	
	public VisualizationViewer<?, Edge> getVisComp() {
		return visComp;
	}
	public void setVisComp(VisualizationViewer<?, Edge> visComp) {
		this.visComp = visComp;
	}
}
