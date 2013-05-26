package utilities.transformers;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import javax.vecmath.Point2d;

import org.apache.commons.collections15.Transformer;

import utilities.enums.EdgeShapeType;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.BentLine;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.CubicCurve;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.Line;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.Orthogonal;
import edu.uci.ics.jung.visualization.decorators.EdgeShape.QuadCurve;

public class GUI_EdgeShapeTransformer implements Transformer<Context<Graph<Vertex, Edge>, Edge>, Shape> {


	public static EdgeShapeType type;
	private VisualizationViewer<Vertex, Edge> vv;
	private Shape edgeShape;	


	public GUI_EdgeShapeTransformer(VisualizationViewer<Vertex, Edge> vviewer){
		type = EdgeShapeType.QUAD_CURVE;
		vv = vviewer;
		edgeShape = null;
	}

	@Override
	public Shape transform(Context<Graph<Vertex, Edge>, Edge> context) {

		switch(type){
		case LINE: edgeShape = (Shape) new Line<Vertex, Edge>().transform(context); break;
		case BENT_LINE: edgeShape = (Shape) new BentLine<Vertex, Edge>().transform(context); break;
		case CUBIC_CURVE: edgeShape = (Shape) new CubicCurve<Vertex, Edge>().transform(context); break;
		case QUAD_CURVE: edgeShape = (Shape) new QuadCurve<Vertex, Edge>().transform(context); break;
		default: edgeShape = (Shape) new Orthogonal<Vertex, Edge>().transform(context); break;
		}
/*
		final Edge e = context.element;
		final RoadNetworkGraph<Vertex, Edge> rng = (RoadNetworkGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

		if(rng.isSelected(e)){

			if(edgeShape instanceof QuadCurve2D){

				final QuadCurve2D curve = (QuadCurve2D) edgeShape;

				if(!e.isAnimated()){
					e.setAnimated(true);

					Thread animateEdge = new Thread(){

						public void run(){

							Vertex tmp = new Vertex();
							vv.getGraphLayout().getGraph().addVertex(tmp);

							Vertex in = rng.getEndpoints(e).getFirst();
							Point2D vPos = null;
				
							double flatness = 0.0001;  
							PathIterator pit = curve.getPathIterator(null, flatness);  
							double[] coords = new double[2];  
							double max = -Double.MAX_VALUE;  
							double min = Double.MAX_VALUE;  
							Point2D.Double lastPoint = new Point2D.Double();  
							while(!pit.isDone()) {  
								int type = pit.currentSegment(coords);  
								switch(type) {  
								case PathIterator.SEG_MOVETO:  
									break;  
								case PathIterator.SEG_LINETO:  
									double dist = lastPoint.distance(coords[0], coords[1]);  
									if(dist < min) min = dist;  
									if(dist > max) max = dist;  
									break;  
								default:  
									System.out.println("Unexpected type: " + type);  
								}  
								lastPoint.setLocation(coords[0], coords[1]); 
								System.out.println("x = " + coords[0] + "\ny = " + coords[1]);
								vPos = vv.getGraphLayout().transform(in);
								vPos.setLocation(vPos.getX() + coords[0], vPos.getY() + coords[1]);
								vv.getGraphLayout().setLocation(tmp, vPos);
								pit.next();  
								vv.repaint();
								try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}  
							System.out.printf("PathIterator: min = %f  max = %f%n", min, max);  


						}

					};
					animateEdge.start();
				}
			}

		}*/

		return edgeShape;
	}

	public EdgeShapeType getType() {
		return type;
	}
	public void setType(EdgeShapeType type) {
		GUI_EdgeShapeTransformer.type = type;
	}
}
