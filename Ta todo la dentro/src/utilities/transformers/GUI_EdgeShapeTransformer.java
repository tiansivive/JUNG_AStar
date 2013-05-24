package utilities.transformers;

import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import utilities.enums.EdgeShapeType;

import dataStructure.graph.Edge;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.BentLine;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.CubicCurve;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.Line;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.Orthogonal;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.QuadCurve;

public class GUI_EdgeShapeTransformer implements Transformer<Context<Graph<Vertex, Edge>, Edge>, Shape> {

	
	public static EdgeShapeType type;
		
	public GUI_EdgeShapeTransformer(){
		type = EdgeShapeType.QUAD_CURVE;
	}
	
	@Override
	public Shape transform(Context<Graph<Vertex, Edge>, Edge> context) {
		
		switch(type){
			case LINE: return new Line<Vertex, Edge>().transform(context);
			case BENT_LINE: return new BentLine<Vertex, Edge>().transform(context);
			case CUBIC_CURVE: return new CubicCurve<Vertex, Edge>().transform(context);
			case QUAD_CURVE: return new QuadCurve<Vertex, Edge>().transform(context);
			default: return  new Orthogonal<Vertex, Edge>().transform(context);
		}
	}

	public EdgeShapeType getType() {
		return type;
	}
	public void setType(EdgeShapeType type) {
		this.type = type;
	}
}
