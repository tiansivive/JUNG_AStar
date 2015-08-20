package utilities.transformers;

import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Vertex;

public class VertexPositionTransformer implements Transformer<Vertex, Point2D> {

	@Override
	public Point2D transform(Vertex v) {
		
		return v.getPosition();
	}

}
