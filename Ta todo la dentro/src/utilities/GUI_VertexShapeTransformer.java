package utilities;

import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Vertex;

public class GUI_VertexShapeTransformer implements Transformer<Vertex,Shape>{


	@Override
	public Shape transform(Vertex arg0) {

		switch(arg0.getType()){
			case U_TURN:
			{
				break;
			}
			case BUILDING_CONNECTION:
			{
				break;
			}
			case INTERSECTION:
			{
				break;
			}
			default:
			{
				break;
			}
		}

		return null;
	}
}
