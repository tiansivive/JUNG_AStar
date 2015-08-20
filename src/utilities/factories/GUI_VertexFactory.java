package utilities.factories;


import org.apache.commons.collections15.Factory;

import utilities.enums.VertexType;

import dataStructure.graph.Vertex;


public class GUI_VertexFactory implements Factory<Vertex>{

	private VertexType type;
	
	public GUI_VertexFactory() {  
		setType(VertexType.INTERSECTION);
	}

	@Override
	public Vertex create() {
		
		/*VertexCreationDialog v = new VertexCreationDialog();
		Point pos = MouseInfo.getPointerInfo().getLocation();

		v.setLocation((int)pos.getX(), (int)pos.getY()); //TODO some kind of offset might be needed
		v.setVisible(true);*/
	
		
		return new Vertex(type);
	}

	public VertexType getType() {
		return type;
	}
	public void setType(VertexType type) {
		this.type = type;
	}
	
	
}
