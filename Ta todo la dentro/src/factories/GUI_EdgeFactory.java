package factories;

import gui.mouse.plugins.menus.EdgePropertyDialog;

import java.awt.MouseInfo;
import java.awt.Point;

import magicNumbers.Values;

import org.apache.commons.collections15.Factory;

import dataStructure.graph.Edge;

public class GUI_EdgeFactory implements Factory<Edge>{

	public static boolean default_mode;

	public GUI_EdgeFactory() {  
		default_mode = true;
	}
	public GUI_EdgeFactory(boolean defaultMode) {  
		default_mode = defaultMode;
	}

	@Override
	public Edge create() {

		Edge e = new Edge(Values.default_edge_speedLimit,
				Values.default_edge_distance,
				Values.default_edge_weight,
				Values.default_edge_capacity,
				Values.default_edge_bidirectionality);

		if(!default_mode){
			EdgePropertyDialog dialog = new EdgePropertyDialog(e);

			Point pos = MouseInfo.getPointerInfo().getLocation();

			dialog.setLocation((int)pos.getX(), (int)pos.getY()); //TODO some kind of offset might be needed
			dialog.setVisible(true);
		}

		return e;
	}


}
