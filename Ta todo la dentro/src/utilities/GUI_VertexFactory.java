package utilities;


import java.awt.MouseInfo;
import java.awt.Point;

import gui.mouse.plugins.menus.dialogs.VertexCreationDialog;

import org.apache.commons.collections15.Factory;

import dataStructure.graph.Vertex;


public class GUI_VertexFactory implements Factory<Vertex>{

	public static boolean default_mode;
	
	public GUI_VertexFactory() {  
		default_mode = true;
	}
	public GUI_VertexFactory(boolean defaultMode) {  
		default_mode = defaultMode;
	}

	@Override
	public Vertex create() {
		
		/*VertexCreationDialog v = new VertexCreationDialog();
		Point pos = MouseInfo.getPointerInfo().getLocation();

		v.setLocation((int)pos.getX(), (int)pos.getY()); //TODO some kind of offset might be needed
		v.setVisible(true);*/
		return new Vertex();
	}
	
	
}
