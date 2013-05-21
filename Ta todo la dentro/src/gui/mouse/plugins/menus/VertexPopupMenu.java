package gui.mouse.plugins.menus;

import javax.swing.JPopupMenu;

import dataStructure.graph.Vertex;

import magicNumbers.Values;

public class VertexPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -3324357514134709241L;
	
	public VertexPopupMenu(){
		super(Values.vertex_popupMenu_name);
		this.add(new DeleteVertexMenuOption<Vertex>());
		this.addSeparator();
		this.add(new SetAsBeginOption<Vertex>());
		this.add(new SetAsEndOption<Vertex>());
		this.add(new SetAsPointToTraverseOption<Vertex>());
		
		//TODO other options
	}
	
}
